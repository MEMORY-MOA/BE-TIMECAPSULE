package com.moa.timecapsule.mapper;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.entity.Timecapsule;

@Mapper(componentModel = "spring")
public interface TimeCapsuleMapper {
	TimeCapsuleDto toDto(Timecapsule timecapsule);

	Timecapsule toEntity(TimeCapsuleDto timeCapsuleDto);

	TimeCapsuleTextDto toDto(TimeCapsuleText timeCapsuleText);

	TimeCapsuleText toEntity(TimeCapsuleTextDto timeCapsuleTextDto);
}
