package de.hsbo.kommonitor.timeseries_management.api.impl.stations;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StationsRepository extends JpaRepository<StationsEntity, Integer> {
	Optional<StationsEntity> findById(Integer id);

	boolean existsById(Integer id);

	void deleteById(Integer id);

}
