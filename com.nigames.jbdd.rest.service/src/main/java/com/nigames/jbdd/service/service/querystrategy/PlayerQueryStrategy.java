package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class PlayerQueryStrategy extends AbstractQueryStrategy<PlayerEntity> {

    @Override
    protected Class<PlayerEntity> getEntityClass() {
        return PlayerEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "nickname", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("deletable");
	}

}
