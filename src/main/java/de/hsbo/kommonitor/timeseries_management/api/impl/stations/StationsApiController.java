package de.hsbo.kommonitor.timeseries_management.api.impl.stations;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.hsbo.kommonitor.timeseries_management.api.StationsApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesDataRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataRepository;
import de.hsbo.kommonitor.timeseries_management.model.ParameterResponse;
import de.hsbo.kommonitor.timeseries_management.model.ParameterResponseAllOfRange;
import de.hsbo.kommonitor.timeseries_management.model.StationMetadata;
import de.hsbo.kommonitor.timeseries_management.model.StationMetadataResponse;
import de.hsbo.kommonitor.timeseries_management.model.TimeseriesData;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("${openapi.komMonitorTimeseriesDataAccess.base-path:}")
public class StationsApiController extends BasePathController implements StationsApi {

	private StationsRepository stationsRepository;

	private ParametersRepository parameterRepository;
	
	private TimeseriesDataRepository timeseriesDataRepository;
	
	private TimeseriesMetadataRepository timeseriesMetadataRepository;
	
	private ObjectMapper objectMapper;
	
	@Autowired
	public StationsApiController(StationsRepository stationRepository, ParametersRepository parameterRepository, 
			TimeseriesDataRepository timeseriesDataRepository, TimeseriesMetadataRepository timeseriesMetadataRepository) {
		this.stationsRepository = stationRepository;
		this.parameterRepository = parameterRepository;
		this.timeseriesDataRepository = timeseriesDataRepository;
		this.timeseriesMetadataRepository = timeseriesMetadataRepository;
		objectMapper = new ObjectMapper();
	}

	@Override
	public ResponseEntity<?> addStationAsBody(@Valid StationMetadata stationMetadata) {
		int id = stationMetadata.getId().intValue();
		if(stationsRepository.existsById(id)) {
			return ResponseEntity.noContent().build();
		}
		URI resultUri = null;
		try {
			StationsEntity entity = StationsMapper.INSTANCE.stationsMetadataToEntity(stationMetadata);
			if(entity.getParameters() != null) {
				Iterator<ParametersEntity> parameterEntityIterator = entity.getParameters().iterator();
				while (parameterEntityIterator.hasNext()) {
					ParametersEntity parameterEntity = (ParametersEntity) parameterEntityIterator.next();
					parameterRepository.saveAndFlush(parameterEntity);
				}
			}
			stationsRepository.saveAndFlush(entity);
			resultUri = new URI(BASE_PATH_KOMMONITOR_API_V1 + "/stations/" + entity.getId());
		} catch (URISyntaxException e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
		return ResponseEntity.created(resultUri).build();
	}

	@Override
	public ResponseEntity<List<StationMetadataResponse>> getStations() {
		List<StationMetadataResponse> result = new ArrayList<StationMetadataResponse>();		
		List<StationsEntity> stationEntities = stationsRepository.findAll();		
		for (StationsEntity stationsEntity : stationEntities) {
			StationMetadataResponse stationMetadataResponse = StationsMapper.INSTANCE.stationsEntityToStationMetadataResponse(stationsEntity);
			
			Set<ParametersEntity> stationsEntityParameters = stationsEntity.getParameters();
			
			List<ParameterResponse> resultParameterResponses = new ArrayList<>();
			
			for (ParametersEntity parametersEntity : stationsEntityParameters) {
				
				ParameterResponse parameterResponse = new ParameterResponse(parametersEntity.getName(), parametersEntity.getUnit());

				Optional<TimeseriesMetadataEntity> timeseriesMetadata = timeseriesMetadataRepository
						.findByStationIdAndParameterId(stationsEntity.getId(), parametersEntity.getId());
				if (timeseriesMetadata.isEmpty()) {
					//continue
					//TODO
				}
				int timeSeriesId = timeseriesMetadata.get().getId();
				String rangeInfo = timeseriesDataRepository.getRange(timeSeriesId);
				try {
					JsonNode rangeInfoNode = objectMapper.readTree(rangeInfo);
					if(rangeInfoNode instanceof ObjectNode) {
					   ObjectNode rangeInfObjectNode = (ObjectNode) rangeInfoNode;
					   ObjectNode lastEntry = (ObjectNode) rangeInfObjectNode.get("last_entry");
					   TimeseriesData data = new TimeseriesData(Float.valueOf(lastEntry.get("value").asText()), OffsetDateTime.parse(lastEntry.get("timestamp").asText()));
					   parameterResponse.setLastEntry(data);
					   ObjectNode rangeObjectNode = (ObjectNode) rangeInfObjectNode.get("range");					   
					   OffsetDateTime start = OffsetDateTime.parse(rangeObjectNode.get("start").asText());
					   OffsetDateTime end = OffsetDateTime.parse(rangeObjectNode.get("end").asText());
					   ParameterResponseAllOfRange range = new ParameterResponseAllOfRange(start, end);
					   parameterResponse.setRange(range);					   
					}
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				resultParameterResponses.add(parameterResponse);
			}
			stationMetadataResponse.setParameters(resultParameterResponses);
			result.add(stationMetadataResponse);
		}
		return ResponseEntity.ok(result);
	}

}
