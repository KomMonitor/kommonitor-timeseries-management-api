package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import de.hsbo.kommonitor.timeseries_management.api.TimeseriesApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersMapper;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.model.Timeseries;
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
	
    @Autowired
    public TimeseriesApiController(TimeseriesMetadataRepository timeseriesRepository, TimeseriesDataRepository timeseriesDataRepository, ParametersRepository parametersRepository) {
    	this.timeseriesMetadataRepository = timeseriesRepository;
    	this.timeseriesDataRepository = timeseriesDataRepository;
    	this.parametersRepository = parametersRepository;
    }

	@Override
	public ResponseEntity<List<Timeseries>> getTimeseries(BigDecimal stationId, BigDecimal parameterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> addTimeseriesAsBody(BigDecimal stationId, BigDecimal parameterId,
			@Valid Timeseries timeseries) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> addTimeseriesMetadataAsBody(@Valid TimeseriesMetadata timeseriesMetadata) {
		URI resultUri = null;
		try {
			ParametersEntity parameterEntity = parametersRepository.saveAndFlush(ParametersMapper.INSTANCE.toDb(timeseriesMetadata.getParameter()));
			timeseriesMetadata.getParameter().setId(new BigDecimal(parameterEntity.getId()));
			TimeseriesMetadataEntity entity = timeseriesMetadataRepository.saveAndFlush(TimeseriesMetadataMapper.INSTANCE.toDb(timeseriesMetadata));
			resultUri = new URI(BASE_PATH_KOMMONITOR_API_V1 + "/timeseries/" + entity.getId());
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		return ResponseEntity.created(resultUri).build();
	}

}
