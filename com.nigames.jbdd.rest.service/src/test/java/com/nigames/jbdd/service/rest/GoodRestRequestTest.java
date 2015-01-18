package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.GoodRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
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
public class GoodRestRequestTest extends AbstractRestRequestTest<Good, GoodRequestInterface> {

    @Test
    public void crudTest() {
        ResteasyWebTarget target = createWebTarget();
        GoodRequestInterface requestInterface = target.proxy(GoodRequestInterface.class);
        crudTest(requestInterface, 0);
    }

    @Override
    protected String getResourcePath() {
        return "/good";
    }

    @Override
    protected Good assembleTestDto(final int index) {
        final Good dto = new Good();
        dto.setEnabled(true);
        dto.setWeight(100 * index);
        dto.setName(I18nGenerator.getNameMap(index));
        dto.setDescription(I18nGenerator.getDescMap(index));
        return dto;
    }

    @Override
    protected void modifyTestDto(final Good dto) {
        dto.setEnabled(!dto.isEnabled());
        dto.setWeight(dto.getWeight() * 2);
        dto.setName(I18nGenerator.getModifiedNameMap(dto));
        dto.setDescription(I18nGenerator.getModifiedDescMap(dto));
    }

    @Override
    protected Good persistTestDto(final Good dto,
                                  final GoodRequestInterface requestInterface) {
        return requestInterface.create(dto);
    }

    @Override
    protected Good updateTestDto(final Good dto,
                                 final GoodRequestInterface requestInterface) {
        return requestInterface.update(dto.getId(), dto);
    }

    @Override
    protected DtoList<Good> getAll(Long first, Long size, String sort,
                                   Boolean desc, final GoodRequestInterface requestInterface) {
        return requestInterface.getAll(first, size, sort, desc);
    }

    @Override
    protected Good getById(long id, GoodRequestInterface requestInterface) {
        return requestInterface.getById(id);
    }

    @Override
    protected void deleteById(long id, GoodRequestInterface requestInterface) {
        requestInterface.deleteById(id);
    }

}
