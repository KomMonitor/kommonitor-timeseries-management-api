package de.hsbo.kommonitor.timeseries_management.api.impl.parameter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParametersRepository extends JpaRepository<ParametersEntity, Integer> {
	
////	@Query("select p from Parameters as p where p.name = :parameter_name")
//	@Query("select p from Parameters p join stations_parameters s on p.id = s.parameters_id where p.name = :parameter_name and s.stations_id = :stations_id")
//	ParametersEntity findByParameterName(@Param("parameter_name") String parameterName, @Param("stations_id") int stationsId);
}
