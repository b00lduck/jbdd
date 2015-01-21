package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;

/**
 * BuildingService interface.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
public interface BuildingService extends
        AbstractDtoServiceInterface<Building, BuildingEntity> {

}
