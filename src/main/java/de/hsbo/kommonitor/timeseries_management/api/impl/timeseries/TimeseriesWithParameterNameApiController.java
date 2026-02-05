package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import de.hsbo.kommonitor.timeseries_management.api.TimeseriesWithParameterNameApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.stations.StationsRepository;
import de.hsbo.kommonitor.timeseries_management.model.TimeseriesData;
import de.hsbo.kommonitor.timeseries_management.model.TimeseriesDataWithParameterName;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-02-04T11:37:59.501537200+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
@Controller
//@RequestMapping("${openapi.komMonitorTimeseriesDataAccess.base-path:}")
public class TimeseriesWithParameterNameApiController extends BasePathController implements TimeseriesWithParameterNameApi {

	TimeseriesMetadataRepository timeseriesMetadataRepository;

	TimeseriesDataRepository timeseriesDataRepository;

	ParametersRepository parametersRepository;

	StationsRepository stationsRepository;
	
    @Autowired
    public TimeseriesWithParameterNameApiController(TimeseriesMetadataRepository timeseriesRepository,
			TimeseriesDataRepository timeseriesDataRepository, ParametersRepository parametersRepository,
			StationsRepository stationsRepository) {
		this.timeseriesMetadataRepository = timeseriesRepository;
		this.timeseriesDataRepository = timeseriesDataRepository;
		this.parametersRepository = parametersRepository;
		this.stationsRepository = stationsRepository;       
    }

	@Override
	public ResponseEntity<?> addTimeseriesAsWithParameterNameBody(BigDecimal stationId,
			@Valid TimeseriesDataWithParameterName timeseriesDataWithParameterName) {
		Optional<StationsEntity> stationsEntity = stationsRepository.findById(stationId.intValue());
		if (stationsEntity.isEmpty()) {
			return ResponseEntity.badRequest().body(String
					.format("Timeseries with station id %d does not exist.", stationId));
		}
		String parameterName = timeseriesDataWithParameterName.getParameterName();
		if(parameterName == null || parameterName.isBlank()) {
			return ResponseEntity.badRequest().body("No parameter name provided.");
			
		}
		int parameterId = -1;
		Set<ParametersEntity> parameters = stationsEntity.get().getParameters();
		for (ParametersEntity parametersEntity : parameters) {
			if(parameterName.equals(parametersEntity.getName())) {
				parameterId = parametersEntity.getId();
				break;
			}
		}
		if(parameterId == -1) {
			return ResponseEntity.badRequest().body(String.format("No parameter found for name %s.", parameterName));			
		}
		Optional<TimeseriesMetadataEntity> timeseriesMetadata = timeseriesMetadataRepository
				.findByStationIdAndParameterId(stationId.intValue(), parameterId);
		if (timeseriesMetadata.isEmpty()) {
			return ResponseEntity.badRequest().body(String
					.format("Timeseries with station id %d and parameter id % ddoes not exist.", stationId, parameterId));
		}
		int timeSeriesId = timeseriesMetadata.get().getId();
		TimeseriesDataEntity timeSeriesDataEntity;
		List<TimeseriesData> timeseriesDataList = timeseriesDataWithParameterName.getTimeseries();
		for (TimeseriesData timeseriesData : timeseriesDataList ) {
			timeSeriesDataEntity = TimeseriesDataMapper.INSTANCE.toDb(timeseriesData);
			timeSeriesDataEntity.setTimeseriesId(timeSeriesId);
			timeseriesDataRepository.save(timeSeriesDataEntity);
		}
		timeseriesDataRepository.flush();
		return ResponseEntity.ok().build();
	}

}
