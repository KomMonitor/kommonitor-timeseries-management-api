package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

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

}
