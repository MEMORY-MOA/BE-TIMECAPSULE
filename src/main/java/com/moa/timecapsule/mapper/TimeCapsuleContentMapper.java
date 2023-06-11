package com.moa.timecapsule.mapper;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleText;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentMapper {
	TimeCapsuleTextDto toDto(TimeCapsuleText timeCapsuleText);

	TimeCapsuleText toEntity(TimeCapsuleTextDto timeCapsuleTextDto);
}
