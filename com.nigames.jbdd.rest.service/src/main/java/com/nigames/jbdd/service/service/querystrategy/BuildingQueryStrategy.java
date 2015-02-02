package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BuildingQueryStrategy extends AbstractQueryStrategy<BuildingEntity> {

    @Override
    protected Class<BuildingEntity> getEntityClass() {
        return BuildingEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("deletable");
	}


}
