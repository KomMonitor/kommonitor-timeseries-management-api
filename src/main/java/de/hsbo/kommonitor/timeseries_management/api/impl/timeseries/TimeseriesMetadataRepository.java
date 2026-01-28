package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimeseriesMetadataRepository extends JpaRepository<TimeseriesMetadataEntity, Integer> {
	
	Optional<TimeseriesMetadataEntity> findById(Integer id);

	@Query("select t from Timeseries_metadata as t where t.stationsId = :stations_id and t.parametersId = :parameters_id")
	Optional<TimeseriesMetadataEntity> findByStationIdAndParameterId(@Param("stations_id")Integer stationsId, @Param("parameters_id")Integer parametersId);
	
	boolean existsById(Integer id);

	void deleteById(Integer id);

}
