package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.aspects.Identifiable;
import com.nigames.jbdd.service.rest.helper.AdminAuthenticator;
import com.nigames.jbdd.statics.Languages;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractRestRequestTest<DtoType extends Identifiable, InterfaceType> extends AbstractRequestTest {

    private static final int TEST_DTO_COUNT = 3;

    /**
     * Persist given DTO to the web service via the given interface.
     *
     * @param dto              DTO to persist
     * @param requestInterface RESTeasy client interface
     * @return
     */
    protected abstract DtoType persistTestDto(final DtoType dto,
                                              final InterfaceType requestInterface);

    /**
     * Update given DTO via the web service via the given interface.
     *
     * @param dto              DTO to update
     * @param requestInterface RESTeasy client interface
     * @return
     */
    protected abstract DtoType updateTestDto(final DtoType dto,
                                             final InterfaceType requestInterface);

    protected abstract DtoList<DtoType> getAll(final Long first,
                                               final Long size, final String sort, final Boolean desc, final InterfaceType requestInterface);

    protected abstract DtoType getById(final long id, final InterfaceType requestInterface);

    protected abstract void deleteById(final long id, final InterfaceType requestInterface);

    protected abstract void modifyTestDto(final DtoType dto);

    protected abstract DtoType assembleTestDto(final int index);

    public void crudTest(final InterfaceType requestInterface, final int initialCount) {

        final Map<Long, DtoType> dtoKeyMap = new HashMap<Long, DtoType>();

        // Get list of DTOs from web service (empty list)
        DtoList<DtoType> dtoList;
        dtoList = getAll(null, null, "id", Boolean.FALSE, requestInterface);
        Assert.assertNotNull(dtoList);

        // Check for initial length of the data array
        Assert.assertEquals(initialCount, dtoList.getData().size());

        // Check for equality of meta.size and actual data size
        Assert.assertEquals(Long.valueOf(dtoList.getData().size()), dtoList.getMeta().getTotalItems());

        // Create a bunch of DTOs
        for (int i = 0; i < TEST_DTO_COUNT; i++) {
            final DtoType newDto = assembleTestDto(i);
            final DtoType dto = persistTestDto(newDto, requestInterface);
            Assert.assertTrue(newDto.equals(dto));
            dtoKeyMap.put(dto.getId(), dto);
        }

        // Get list of DTOs from web service
        dtoList = getAll(null, null, "id", Boolean.FALSE, requestInterface);
        Assert.assertNotNull(dtoList);

        // Check for correct length of the data array
        Assert.assertEquals(TEST_DTO_COUNT + initialCount, dtoList.getData().size());

        // Check for equality of meta.size and actual data size
        Assert.assertEquals(Long.valueOf(dtoList.getData().size()), dtoList
                .getMeta().getTotalItems());

        // Verify-Update-Verify every single DTO in database
        for (Map.Entry<Long, DtoType> entry : dtoKeyMap.entrySet()) {
            // Verify
            DtoType returnedDto = getById(entry.getKey(), requestInterface);
            Assert.assertEquals(entry.getValue(), returnedDto);

            // Update
            modifyTestDto(returnedDto);
            DtoType updatedDto = updateTestDto(returnedDto, requestInterface);
            Assert.assertEquals(updatedDto, returnedDto);

            // Verify
            DtoType returnedDtoVerify = getById(returnedDto
                    .getId(), requestInterface);
            Assert.assertEquals(returnedDtoVerify, returnedDto);
        }

        // Delete every DTO by dtoKeyMap
        for (Map.Entry<Long, DtoType> entry : dtoKeyMap.entrySet()) {
            deleteById(entry.getKey(), requestInterface);
        }

        // Check if only intial DTOs are left in the database
        dtoList = getAll(null, null, "id", Boolean.FALSE, requestInterface);
        Assert.assertEquals(initialCount, dtoList.getData().size());
        Assert.assertEquals(Long.valueOf(dtoList.getData().size()), dtoList
                .getMeta().getTotalItems());

    }

    protected Map<String, String> assembleTestI18nMap(final String prefix) {
        final Map<String, String> ret = new HashMap<String, String>();
        for (String lang : Languages.getLanguageTagList()) {
            ret.put(lang, prefix + "_" + lang);
        }
        return ret;
    }

    protected void modifyTestI18nMap(final Map<String, String> src) {
        for (Map.Entry<String, String> entry : src.entrySet()) {
            entry.setValue(entry.getValue() + "_modified");
        }
    }

    protected ResteasyWebTarget createWebTarget() {
        final ResteasyClientBuilder resteasyClientBuilder = new ResteasyClientBuilder();
        final ResteasyClient client = resteasyClientBuilder.build();
        client.register(new AdminAuthenticator());
        return client.target(BASE_URL);
    }
}
