package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import org.springframework.stereotype.Component;

@Component
public class CostQueryStrategy extends AbstractQueryStrategy<CostEntity> {

	@Override
	protected Class<CostEntity> getEntityClass() {
		return CostEntity.class;
	}

}
