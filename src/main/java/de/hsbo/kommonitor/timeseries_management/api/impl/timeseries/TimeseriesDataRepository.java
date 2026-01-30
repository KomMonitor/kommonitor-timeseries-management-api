package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimeseriesDataRepository extends JpaRepository<TimeseriesDataEntity, Integer> {
	
	Optional<TimeseriesDataEntity> findById(Integer id);

	boolean existsById(Integer id);

	void deleteById(Integer id);

    @Query("select t from Timeseries_data as t where t.timeseriesId = :timeseries_id")
	List<TimeseriesDataEntity> findByTimeSeriesId(@Param("timeseries_id") Integer timeseriesId);
    
//    @Query("select time_bucket(cast('1 day' as interval), timestamp), avg(value) from Timeseries_data where timeseriesId = :timeseries_id group by 1")
    @NativeQuery("select time_bucket('1 day'\\:\\:interval, timestamp), avg(value) from Timeseries_data where timeseries_data.timeseries_id = :timeseries_id group by 1")
    List<Map<String, Object>> getAggregate(@Param("timeseries_id") Integer timeseriesId, @Param("function") String function);

}
