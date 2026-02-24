package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimeseriesDataRepository extends JpaRepository<TimeseriesDataEntity, Integer> {

	Optional<TimeseriesDataEntity> findById(Integer id);

	boolean existsById(Integer id);

	void deleteById(Integer id);

	@Query("select t from Timeseries_data as t where t.timeseriesId = :timeseries_id")
	List<TimeseriesDataEntity> findByTimeSeriesId(@Param("timeseries_id") Integer timeseriesId);

//    @Query("select t from get_timeseries(:timeseries_id, to_timestamp(:start, 'YYYY-MM-dd''T''hh:mi:ss'), to_timestamp(:end, 'YYYY-MM-dd''T''hh:mi:ss')) as t")
//	List<TimeseriesDataEntity> findByTimeSeriesIdStartEnd(@Param("timeseries_id") Integer timeseriesId, @Param("start") String start, @Param("end") String end);

	@Query("select t from Timeseries_data as t where t.timeseriesId = :timeseries_id and  (:start is null or t.timestamp > to_timestamp(cast(:start as text), cast('YYYY-MM-dd''T''hh:mi:ss' as text))) and  (:end is null or t.timestamp < to_timestamp(cast(:end as text), cast('YYYY-MM-dd''T''hh:mi:ss' as text)))")
	List<TimeseriesDataEntity> findByTimeSeriesIdStartEnd(@Param("timeseries_id") Integer timeseriesId,
			@Param("start") String start, @Param("end") String end);

	@Query("select aggregate_time(:timeseries_id, :interval, :function, to_timestamp(cast(:start as text), cast('YYYY-MM-dd''T''hh:mi:ss' as text)), to_timestamp(cast(:end as text), cast('YYYY-MM-dd''T''hh:mi:ss' as text)))")
	String getAggregate(@Param("timeseries_id") Integer timeseriesId, @Param("interval") String interval,
			@Param("function") String function, @Param("start") String start, @Param("end") String end);

	@Query("select get_range(:timeseries_id)")
	String getRange(@Param("timeseries_id") Integer timeseriesId);
}
