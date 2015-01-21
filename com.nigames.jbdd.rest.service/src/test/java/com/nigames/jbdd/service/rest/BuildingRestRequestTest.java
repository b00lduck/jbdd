package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.BuildingRequestInterface;
import com.nigames.jbdd.rest.api.GoodRequestInterface;
import com.nigames.jbdd.rest.dto.Building;
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
public class BuildingRestRequestTest extends AbstractRestRequestTest<Building, BuildingRequestInterface> {

    @Test
    public void crudTest() {
        ResteasyWebTarget target = createWebTarget();
        BuildingRequestInterface requestInterface = target.proxy(BuildingRequestInterface.class);
        crudTest(requestInterface, 0);
    }

    @Override
    protected String getResourcePath() {
        return "/building";
    }

    @Override
    protected Building assembleTestDto(final int index) {
        final Building dto = new Building();
        dto.setEnabled(true);
        dto.setName(I18nGenerator.getNameMap(index));
        dto.setDescription(I18nGenerator.getDescMap(index));
        return dto;
    }

    @Override
    protected void modifyTestDto(final Building dto) {
        dto.setEnabled(!dto.isEnabled());
        dto.setName(I18nGenerator.getModifiedNameMap(dto));
        dto.setDescription(I18nGenerator.getModifiedDescMap(dto));
    }

    @Override
    protected Building persistTestDto(final Building dto,
                                  final BuildingRequestInterface requestInterface) {
        return requestInterface.create(dto);
    }

    @Override
    protected Building updateTestDto(final Building dto,
                                 final BuildingRequestInterface requestInterface) {
        return requestInterface.update(dto.getId(), dto);
    }

    @Override
    protected DtoList<Building> getAll(Long first, Long size, String sort,
                                   Boolean desc, final BuildingRequestInterface requestInterface) {
        return requestInterface.getAll(first, size, sort, desc);
    }

    @Override
    protected Building getById(long id, BuildingRequestInterface requestInterface) {
        return requestInterface.getById(id);
    }

    @Override
    protected void deleteById(long id, BuildingRequestInterface requestInterface) {
        requestInterface.deleteById(id);
    }

}
