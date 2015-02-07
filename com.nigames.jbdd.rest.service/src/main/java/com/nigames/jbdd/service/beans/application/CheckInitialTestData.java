package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.rest.exceptionprovider.ContentNotFoundException;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInitialTestData extends AbstractCheckUserBean {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private GoodService goodService;

	public void doChecks() {

		long id = 1;

		createBuilding(id++);
		createBuilding(id++);
		createBuilding(id++);
		createBuilding(id++);
		createBuilding(id++);

		createGood(id++);
		createGood(id++);
		createGood(id++);
		createGood(id++);
		createGood(id++);

	}

	private void createBuilding(long id) {
		try {
			buildingService.findById(id);
		} catch (ContentNotFoundException e) {
			final Building b = new Building();
			b.setBuildtime((int) id * 100);
			b.setEnabled((id % 2) == 0);

			b.getName().put("de-DE", "B" + id);
			b.getName().put("en-GB", "B" + id);
			b.getDescription().put("de-DE", "Description B" + id);
			b.getDescription().put("en-GB", "Description B" + id);

			buildingService.create(b);
		}

	}

	private void createGood(long id) {
		try {
			goodService.findById(id);
		} catch (ContentNotFoundException e) {
			final Good b = new Good();
			b.setEnabled((id % 2) == 0);

			b.getName().put("de-DE", "B" + id);
			b.getName().put("en-GB", "B" + id);
			b.getDescription().put("de-DE", "Description B" + id);
			b.getDescription().put("en-GB", "Description B" + id);

			goodService.create(b);
		}

	}

}
