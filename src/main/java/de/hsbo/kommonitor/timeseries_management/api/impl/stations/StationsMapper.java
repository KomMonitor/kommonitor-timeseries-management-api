package de.hsbo.kommonitor.timeseries_management.api.impl.stations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.hsbo.kommonitor.timeseries_management.api.impl.parameter.ParametersEntity;
import de.hsbo.kommonitor.timeseries_management.model.Parameter;
import de.hsbo.kommonitor.timeseries_management.model.ParameterResponse;
import de.hsbo.kommonitor.timeseries_management.model.Position;
import de.hsbo.kommonitor.timeseries_management.model.StationMetadata;
import de.hsbo.kommonitor.timeseries_management.model.StationMetadataResponse;
import jakarta.validation.Valid;

@Mapper
public interface StationsMapper {

	StationsMapper INSTANCE = Mappers.getMapper(StationsMapper.class);

	GeometryFactory geometryFactory = new GeometryFactory();

	default StationsEntity stationsMetadataToEntity(StationMetadata stationMetadata) {
		Position position = stationMetadata.getPosition();
		Coordinate coordinate = new Coordinate(position.getX().doubleValue(), position.getY().doubleValue());
		StationsEntity result = new StationsEntity(stationMetadata.getId().intValue(), stationMetadata.getName(),
				geometryFactory.createPoint(coordinate));
		return result;
	}
	
	default StationsEntity stationsMetadataToEntity(StationMetadataResponse stationMetadata) {
		Position position = stationMetadata.getPosition();
		Coordinate coordinate = new Coordinate(position.getX().doubleValue(), position.getY().doubleValue());
		StationsEntity result = new StationsEntity(stationMetadata.getId().intValue(), stationMetadata.getName(),
				geometryFactory.createPoint(coordinate));
		@Valid List<ParameterResponse> parametersFromMetadata = stationMetadata.getParameters();
		Set<ParametersEntity> parameterEntities = new HashSet<ParametersEntity>();
		for (ParameterResponse parameter : parametersFromMetadata) {
			ParametersEntity parameterEntity = new ParametersEntity(parameter.getName(), parameter.getUnit());
			parameterEntities.add(parameterEntity);
		}
		result.setParameters(parameterEntities);
		return result;
	}
	
	default StationMetadataResponse stationsEntityToStationMetadataResponse(StationsEntity stationsEntity) {
		StationMetadataResponse result = new StationMetadataResponse();
		
		result.setId(BigDecimal.valueOf(stationsEntity.getId()));
		result.setName(stationsEntity.getName());
		Point stationsEntityPosition = stationsEntity.getPosition();
		result.setPosition(new Position(BigDecimal.valueOf(stationsEntityPosition.getX()), BigDecimal.valueOf(stationsEntityPosition.getY())));
		return result;
	}

}
