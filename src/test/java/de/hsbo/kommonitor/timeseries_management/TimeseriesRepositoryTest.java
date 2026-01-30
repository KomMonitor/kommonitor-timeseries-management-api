package de.hsbo.kommonitor.timeseries_management;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesDataEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesDataRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataRepository;

public class TimeseriesRepositoryTest extends RepositoryTest {

	@Autowired
	StationsRepository stationRepository;

	@Autowired
	ParametersRepository parameterRepository;

	@Autowired
	public TimeseriesMetadataRepository timeseriesMetadataRepository;

	@Autowired
	public TimeseriesDataRepository timeseriesDataRepository;

	@Test
	void testAddTimeseries() {
		Integer stationsId = 1;
		ParametersEntity parameterEntity = new ParametersEntity("test_parameter", "test_unit");
		parameterRepository.saveAndFlush(parameterEntity);
		StationsEntity stationEntity = new StationsEntity(stationsId, "test_station");
		stationEntity.addParameter(parameterEntity);
		stationRepository.saveAndFlush(stationEntity);
		Integer parametersId = parameterEntity.getId();
		TimeseriesMetadataEntity timeseriesMetadataEntity = new TimeseriesMetadataEntity(stationsId, parametersId);
		timeseriesMetadataRepository.saveAndFlush(timeseriesMetadataEntity);
		assertEquals(timeseriesMetadataRepository.count(), 1);
		Optional<TimeseriesMetadataEntity> timeseriesMetadataEntityFromDb = timeseriesMetadataRepository.findByStationIdAndParameterId(stationsId, parametersId);
		assertTrue(timeseriesMetadataEntityFromDb.isPresent());
		Integer timeseriesId = timeseriesMetadataEntityFromDb.get().getId();
		TimeseriesDataEntity timeseriesDataEntity = new TimeseriesDataEntity(timeseriesId, 0.1f, new Date());
		timeseriesDataRepository.saveAndFlush(timeseriesDataEntity);
		List<TimeseriesDataEntity> timeseriesEntities = timeseriesDataRepository.findByTimeSeriesId(timeseriesId);
		assertNotNull(timeseriesEntities);
		assertEquals(timeseriesEntities.size(), 1);
		String function = "avg";
		String interval = "1 day";
		String aggregate = timeseriesDataRepository.getAggregate(timeseriesId, interval, function);
		assertNotNull(aggregate);
//		for (Map<String, Object> map : aggregate) {
//			Object value = map.get(function);
//			Object time = map.get("time_bucket");
//			if(time instanceof Instant) {
//				Date date = Date.from((Instant)time);
//			}
//			result.add(new TimeseriesAggregateDataValue(,  value));
//		}
	}

}
