package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * TechnologyService interface.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.TechnologyServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_TECHNOLOGY)
public interface TechnologyService extends AbstractDtoServiceInterface<Technology> {

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_TECHNOLOGY)
	Technology findById(final Long entityId);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_TECHNOLOGY)
	Technology create(final Technology dto);

	@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_COST)
	List<Technology> findAllEnabled();

}
