package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.hsbo.kommonitor.timeseries_management.model.TimeseriesData;

@Mapper
public interface TimeseriesDataMapper {

	ZoneId zone = ZoneId.of("Europe/Berlin");

	TimeseriesDataMapper INSTANCE = Mappers.getMapper(TimeseriesDataMapper.class);
	
	default TimeseriesDataEntity toDb(TimeseriesData timeseriesData) {
		float value = timeseriesData.getValue().floatValue();
		OffsetDateTime timestamp = timeseriesData.getTimestamp();
		TimeseriesDataEntity result = new TimeseriesDataEntity(value, Date.from(timestamp.toInstant()));
		return result;
	}

	default TimeseriesData fromDb(TimeseriesDataEntity entity) {
		Float value = entity.getValue();
		Date timestamp = entity.getTimestamp();
		ZoneOffset zoneOffset = zone.getRules().getOffset(timestamp.toInstant());
		TimeseriesData result = new TimeseriesData(new BigDecimal(value),
				timestamp.toInstant().atOffset(zoneOffset));
		return result;
	}
}
