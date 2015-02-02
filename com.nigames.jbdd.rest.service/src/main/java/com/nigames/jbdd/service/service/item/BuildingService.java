package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
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
public interface BuildingService extends AbstractDtoServiceInterface<Building, BuildingEntity> {

}
