package de.hsbo.kommonitor.timeseries_management.api.impl.timeseries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TimeseriesMetadataRepository extends JpaRepository<TimeseriesMetadataEntity, Integer> {
	
	Optional<TimeseriesMetadataEntity> findById(Integer id);

	boolean existsById(Integer id);

	void deleteById(Integer id);

}
