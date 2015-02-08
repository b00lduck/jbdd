package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.service.rest.exceptionprovider.ContentNotFoundException;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.item.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckInitialTestData extends AbstractCheckUserBean {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private GoodService goodService;

	@Autowired
	private TechnologyService technologyService;

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

		createTechnology(id++);
		createTechnology(id++);
		createTechnology(id++);
		createTechnology(id++);
		createTechnology(id++);

	}

	private void createBuilding(long id) {
		try {
			buildingService.findById(id);
		} catch (ContentNotFoundException e) {
			final Building b = new Building();
			b.setBuildtime((int) id * 100);
			b.setEnabled((id % 2) == 0);

			b.getName().put("de-DE", "Gebäude #" + id);
			b.getName().put("en-GB", "Building #" + id);
			b.getDescription().put("de-DE", "Beschreibung Gebäude #" + id);
			b.getDescription().put("en-GB", "Description Builidng #" + id);

			buildingService.create(b);
		}

	}

	private void createTechnology(long id) {
		try {
			technologyService.findById(id);
		} catch (ContentNotFoundException e) {
			final Technology t = new Technology();
			t.setBuildtime((int) id * 100);
			t.setEnabled((id % 2) == 0);

			t.getName().put("de-DE", "Technologie #" + id);
			t.getName().put("en-GB", "Technology #" + id);
			t.getDescription().put("de-DE", "Beschreibung Technologie #" + id);
			t.getDescription().put("en-GB", "Description Technology #" + id);

			technologyService.create(t);
		}

	}

	private void createGood(long id) {
		try {
			goodService.findById(id);
		} catch (ContentNotFoundException e) {
			final Good b = new Good();
			b.setEnabled((id % 2) == 0);

			b.getName().put("de-DE", "Gut #" + id);
			b.getName().put("en-GB", "Good #" + id);
			b.getDescription().put("de-DE", "Beschreibung Gut #" + id);
			b.getDescription().put("en-GB", "Description Good #" + id);

			goodService.create(b);
		}

	}

}
