package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.JobConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import com.nigames.jbdd.service.conversion.impl.modules.NameDescConversionServiceTestModule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test for the JobConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class JobConversionServiceTest extends AbstractConversionServiceTest<Job, JobEntity> {

	@Autowired
	private transient JobConversionService jobConversionService;

	@Override
	protected JobConversionService getConversionService() {
		return jobConversionService;
	}

	@Override
	protected void addModules() {
		addModule(new NameDescConversionServiceTestModule());
		addModule(new EnabledIdConversionServiceTestModule());
	}

	@Override
	protected void fillEntity(final JobEntity entity) {
	}

	@Override
	protected void checkDto(final Job dto) {
	}

	@Override
	protected void fillDto(final Job dto) {
	}

	@Override
	protected void checkEntity(final JobEntity entity) {
	}

}
