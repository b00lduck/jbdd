package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.HAS_ROLE_ADMIN_STORAGETYPE;
/**
 * StoragetypeService interface.
 *
 * @author Daniel
 * @see StoragetypeServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_STORAGETYPE)
public interface StoragetypeService extends AbstractDtoServiceInterface<Storagetype> {

}
