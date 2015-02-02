package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class StoragetypeQueryStrategy extends AbstractQueryStrategy<StoragetypeEntity> {

    @Override
    protected Class<StoragetypeEntity> getEntityClass() {
        return StoragetypeEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("deletable");
	}

}
