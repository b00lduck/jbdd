package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * BuildingService interface.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_BUILDING)
public interface BuildingService extends AbstractDtoServiceInterface<Building> {

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_BUILDING)
	Building findById(final Long entityId);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_BUILDING)
	Building create(final Building dto);

	@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_REQUIREMENT)
	List<Building> findAllEnabled();

}
