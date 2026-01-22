package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.time.ZoneId;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.hsbo.kommonitor.timeseries_management.model.TimeseriesMetadata;

@Mapper
public interface TimeseriesMetadataMapper {

	ZoneId zone = ZoneId.of("Europe/Berlin");

	TimeseriesMetadataMapper INSTANCE = Mappers.getMapper(TimeseriesMetadataMapper.class);

	default TimeseriesMetadataEntity toDb(TimeseriesMetadata timeseriesMetadata) {
		int stationId = timeseriesMetadata.getStationId().intValue();
		int parameterId = timeseriesMetadata.getParameter().getId().intValue();
		TimeseriesMetadataEntity result = new TimeseriesMetadataEntity(stationId, parameterId);
		return result;
	}
}
