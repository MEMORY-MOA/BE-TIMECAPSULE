package com.moa.timecapsule.mapper;

import com.moa.timecapsule.dto.BaseDto;
import com.moa.timecapsule.entity.BaseEntity;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BaseMapper {
	BaseMapper instance = Mappers.getMapper(BaseMapper.class);

	BaseEntity dtoToEntity(BaseDto baseDto);

	BaseDto entityToDto(BaseEntity baseEntity);
}
