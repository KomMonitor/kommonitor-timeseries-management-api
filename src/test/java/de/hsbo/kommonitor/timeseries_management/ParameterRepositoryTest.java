package de.hsbo.kommonitor.timeseries_management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;

public class ParameterRepositoryTest extends RepositoryTest {

	@Autowired
	ParametersRepository parametersRepository;

	@Autowired
	StationsRepository stationRepository;
	
	@Test
	public void testGetParameterByName() {
		StationsEntity entity = new StationsEntity(1, "test_station");		
		stationRepository.saveAndFlush(entity);
		String testName = "test";
		ParametersEntity parametersEntity = new ParametersEntity(testName , "testUnit");
		ParametersEntity parametersEntityFromDb = parametersRepository.saveAndFlush(parametersEntity);
        int parametersId = parametersEntityFromDb.getId();
	}
	
}
