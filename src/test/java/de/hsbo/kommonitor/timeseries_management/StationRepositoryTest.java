package de.hsbo.kommonitor.timeseries_management;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;

public class StationRepositoryTest extends RepositoryTest {

	@Autowired
	StationsRepository stationRepository;
	
	@Test
	void testAddStation() {
		StationsEntity entity = new StationsEntity(1, "test_station");
		
		stationRepository.saveAndFlush(entity);
	}
	
}
