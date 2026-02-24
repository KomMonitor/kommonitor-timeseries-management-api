package de.hsbo.kommonitor.timeseries_management.api.impl.aggregates;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import de.hsbo.kommonitor.timeseries_management.api.AggregatesApi;
import de.hsbo.kommonitor.timeseries_management.api.impl.BasePathController;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesDataRepository;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataEntity;
import de.hsbo.kommonitor.timeseries_management.api.impl.timeseries.TimeseriesMetadataRepository;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2026-01-29T13:52:28.162177800+01:00[Europe/Berlin]", comments = "Generator version: 7.13.0")
@Controller
//@RequestMapping("${openapi.komMonitorTimeseriesDataAccess.base-path:}")
public class AggregatesApiController extends BasePathController implements AggregatesApi {
	
	TimeseriesMetadataRepository timeseriesMetadataRepository;
	
	TimeseriesDataRepository timeseriesDataRepository;
	
    @Autowired
    public AggregatesApiController(TimeseriesMetadataRepository timeseriesMetadataRepository, TimeseriesDataRepository timeseriesDataRepository) {
        this.timeseriesDataRepository = timeseriesDataRepository;
        this.timeseriesMetadataRepository = timeseriesMetadataRepository;
    }

	@Override
	public ResponseEntity<?> getTimeseriesAggregates(BigDecimal stationId, BigDecimal parameterId,
			@NotNull @Valid String frequency, @NotNull @Valid String function, @Valid OffsetDateTime start,
			@Valid OffsetDateTime end) {
		Optional<TimeseriesMetadataEntity> timeseriesMetadata = timeseriesMetadataRepository
				.findByStationIdAndParameterId(stationId.intValue(), parameterId.intValue());
		if (timeseriesMetadata.isEmpty()) {
			return ResponseEntity.badRequest().body(String
					.format("Timeseries with station id %d and parameter id does not exist.", stationId, parameterId));
		}
		int timeSeriesId = timeseriesMetadata.get().getId();
		String startString = start != null ? start.toString() : null;
		String endString = end != null ? end.toString() : null;
		String aggregates = timeseriesDataRepository.getAggregate(timeSeriesId, frequency, function, startString, endString);
		return ResponseEntity.ok(aggregates);
	}

}
