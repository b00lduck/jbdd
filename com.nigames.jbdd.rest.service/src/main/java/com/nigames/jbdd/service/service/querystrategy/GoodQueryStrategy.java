package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GoodQueryStrategy extends AbstractQueryStrategy<GoodEntity> {

    @Override
    protected Class<GoodEntity> getEntityClass() {
        return GoodEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("deletable");
	}

}
