package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.StoragetypeRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.rest.helper.I18nGenerator;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
@IntegrationTest("server.port:8888")
public class StoragetypeRestRequestTest extends AbstractRestRequestTest<Storagetype, StoragetypeRequestInterface> {

    @Test
    public void crudTest() {
        ResteasyWebTarget target = createWebTarget();
        StoragetypeRequestInterface requestInterface = target.proxy(StoragetypeRequestInterface.class);
        crudTest(requestInterface, 0);
    }

    @Override
    protected String getResourcePath() {
        return "/storagetype";
    }

    @Override
    protected Storagetype assembleTestDto(final int index) {
        final Storagetype dto = new Storagetype();
        dto.setEnabled(true);
        dto.setName(I18nGenerator.getNameMap(index));
        dto.setDescription(I18nGenerator.getDescMap(index));
        return dto;
    }

    @Override
    protected void modifyTestDto(final Storagetype dto) {
        dto.setEnabled(!dto.isEnabled());
        dto.setName(I18nGenerator.getModifiedNameMap(dto));
        dto.setDescription(I18nGenerator.getModifiedDescMap(dto));
    }

    @Override
    protected Storagetype persistTestDto(final Storagetype dto,
                                         final StoragetypeRequestInterface requestInterface) {
        return requestInterface.create(dto);
    }

    @Override
    protected Storagetype updateTestDto(final Storagetype dto,
                                        final StoragetypeRequestInterface requestInterface) {
        return requestInterface.update(dto.getId(), dto);
    }

    @Override
    protected DtoList<Storagetype> getAll(Long first, Long size, String sort,
                                          Boolean desc, final StoragetypeRequestInterface requestInterface) {
        return requestInterface.getAll(first, size, sort, desc);
    }

    @Override
    protected Storagetype getById(long id, StoragetypeRequestInterface requestInterface) {
        return requestInterface.getById(id);
    }

    @Override
    protected void deleteById(long id, StoragetypeRequestInterface requestInterface) {
        requestInterface.deleteById(id);
    }

}
