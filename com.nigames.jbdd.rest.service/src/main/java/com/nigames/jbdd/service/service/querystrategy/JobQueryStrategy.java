package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class JobQueryStrategy extends AbstractQueryStrategy<JobEntity> {

    @Override
    protected Class<JobEntity> getEntityClass() {
        return JobEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("deletable");
	}

}
