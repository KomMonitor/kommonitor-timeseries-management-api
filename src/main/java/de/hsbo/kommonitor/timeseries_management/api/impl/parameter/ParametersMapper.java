package de.hsbo.kommonitor.timeseries_management.api.impl.parameter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import de.hsbo.kommonitor.timeseries_management.model.Parameter;

@Mapper
public interface ParametersMapper {

	ParametersMapper INSTANCE = Mappers.getMapper(ParametersMapper.class);

	ParametersEntity toDb(Parameter parameter);
}
