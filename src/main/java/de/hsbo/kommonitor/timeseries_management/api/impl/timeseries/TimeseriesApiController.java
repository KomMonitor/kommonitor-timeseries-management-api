package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import de.hsbo.kommonitor.timeseries_management.api.TimeseriesApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersMapper;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;
import de.hsbo.kommonitor.timeseries_management.model.TimeseriesData;
import de.hsbo.kommonitor.timeseries_management.model.TimeseriesMetadata;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-12-03T09:03:10.993959800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
@Controller
//@RequestMapping("${openapi.komMonitorTimeseriesDataAccess.base-path:}")
public class TimeseriesApiController extends BasePathController implements TimeseriesApi {
	
	TimeseriesMetadataRepository timeseriesMetadataRepository;
	
	TimeseriesDataRepository timeseriesDataRepository;
	
	ParametersRepository parametersRepository;
	
	StationsRepository stationsRepository;
	
    @Autowired
    public TimeseriesApiController(TimeseriesMetadataRepository timeseriesRepository, TimeseriesDataRepository timeseriesDataRepository, ParametersRepository parametersRepository, StationsRepository stationsRepository) {
    	this.timeseriesMetadataRepository = timeseriesRepository;
    	this.timeseriesDataRepository = timeseriesDataRepository;
    	this.parametersRepository = parametersRepository;
    	this.stationsRepository = stationsRepository;
    }

	@Override
	public ResponseEntity<?> getTimeseries(BigDecimal stationId, BigDecimal parameterId) {
		Optional<TimeseriesMetadataEntity> timeseriesMetadata = timeseriesMetadataRepository.findByStationIdAndParameterId(stationId.intValue(), parameterId.intValue());
		if(timeseriesMetadata.isEmpty()) {
			return ResponseEntity.badRequest().body(String.format("Timeseries with station id %d and parameter id does not exist.", stationId, parameterId));
		}
		int timeSeriesId = timeseriesMetadata.get().getId();
		List<TimeseriesData> resultList = new ArrayList<>();
		List<TimeseriesDataEntity> timeseriesDataList = timeseriesDataRepository.findByTimeSeriesId(timeSeriesId);
		for (TimeseriesDataEntity timeseriesDataEntity : timeseriesDataList) {
			resultList.add(TimeseriesDataMapper.INSTANCE.fromDb(timeseriesDataEntity));
		}
		return ResponseEntity.ok(resultList);
	}

	@Override
	public ResponseEntity<?> addTimeseriesMetadataAsBody(@Valid TimeseriesMetadata timeseriesMetadata) {
		URI resultUri = null;
		try {
			int stationId = timeseriesMetadata.getStationId().intValue();
			Optional<StationsEntity> station = stationsRepository.findById(stationId);
			if(station.isEmpty()) {
				return ResponseEntity.badRequest().body(String.format("Station with id %d does not exist.", stationId));
			}			
			ParametersEntity parameterEntity = parametersRepository.saveAndFlush(ParametersMapper.INSTANCE.toDb(timeseriesMetadata.getParameter()));
			station.get().addParameter(parameterEntity);
			timeseriesMetadata.getParameter().setId(new BigDecimal(parameterEntity.getId()));
			timeseriesMetadataRepository.saveAndFlush(TimeseriesMetadataMapper.INSTANCE.toDb(timeseriesMetadata));
			resultUri = new URI(String.format(BASE_PATH_KOMMONITOR_API_V1 + "/timeseries/%d/%d", stationId, parameterEntity.getId()));
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		return ResponseEntity.created(resultUri).build();
	}

	@Override
	public ResponseEntity<?> addTimeseriesAsBody(BigDecimal stationId, BigDecimal parameterId,
			@Valid List<@Valid TimeseriesData> timeseriesDataList) {
		Optional<TimeseriesMetadataEntity> timeseriesMetadata = timeseriesMetadataRepository.findByStationIdAndParameterId(stationId.intValue(), parameterId.intValue());
		if(timeseriesMetadata.isEmpty()) {
			return ResponseEntity.badRequest().body(String.format("Timeseries with station id %d and parameter id does not exist.", stationId, parameterId));
		}
		int timeSeriesId = timeseriesMetadata.get().getId();
		TimeseriesDataEntity timeSeriesDataEntity;
		for (TimeseriesData timeseriesData : timeseriesDataList) {
			timeSeriesDataEntity = TimeseriesDataMapper.INSTANCE.toDb(timeseriesData);
			timeSeriesDataEntity.setTimeseriesId(timeSeriesId);
			timeseriesDataRepository.save(timeSeriesDataEntity);
		}		
		timeseriesDataRepository.flush();
		return null;
	}

}
