package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * GoodService interface.
 *
 * @author Daniel
 * @see GoodServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_GOOD)
public interface GoodService extends AbstractDtoServiceInterface<Good> {

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_GOOD)
	Good findById(final Long entityId);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_GOOD)
	Good create(final Good dto);

	@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_COST)
	List<Good> findAllEnabled();

}
