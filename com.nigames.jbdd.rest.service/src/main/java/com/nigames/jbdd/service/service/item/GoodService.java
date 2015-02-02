package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * GoodService interface.
 *
 * @author Daniel
 * @see GoodServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_GOOD)
public interface GoodService extends AbstractDtoServiceInterface<Good, GoodEntity> {

}
