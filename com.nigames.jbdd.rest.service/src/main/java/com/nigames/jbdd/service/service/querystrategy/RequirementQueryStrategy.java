package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import org.springframework.stereotype.Component;

@Component
public class RequirementQueryStrategy extends AbstractQueryStrategy<RequirementEntity> {

	@Override
	protected Class<RequirementEntity> getEntityClass() {
		return RequirementEntity.class;
	}

}
