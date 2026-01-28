package de.hsbo.kommonitor.timeseries_management.api.impl.stations;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import de.hsbo.kommonitor.timeseries_management.api.StationsApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersRepository;
import de.hsbo.kommonitor.timeseries_management.model.StationMetadata;
import jakarta.validation.Valid;

@Controller
//@RequestMapping("${openapi.komMonitorTimeseriesDataAccess.base-path:}")
public class StationsApiController extends BasePathController implements StationsApi {

	private StationsRepository stationsRepository;

	private ParametersRepository parameterRepository;
	
	@Autowired
	public StationsApiController(StationsRepository stationRepository, ParametersRepository parameterRepository) {
		this.stationsRepository = stationRepository;
		this.parameterRepository = parameterRepository;
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
	public ResponseEntity<List<StationMetadata>> getStations() {
		List<StationMetadata> result = new ArrayList<StationMetadata>();		
		List<StationsEntity> stationEntities = stationsRepository.findAll();		
		for (StationsEntity stationsEntity : stationEntities) {
			result.add(StationsMapper.INSTANCE.stationsEntityToStationMetadata(stationsEntity)); 
		}		
		return ResponseEntity.ok(result);
	}

}
