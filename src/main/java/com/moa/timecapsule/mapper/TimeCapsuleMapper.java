package com.moa.timecapsule.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.entity.Timecapsule;

@Mapper(componentModel = "spring")
public interface TimeCapsuleMapper {
	TimeCapsuleDto toDto(Timecapsule timecapsule);

	Timecapsule toEntity(TimeCapsuleDto timeCapsuleDto);

	List<TimeCapsuleDto> toDto(List<Timecapsule> timecapsuleList);

	List<Timecapsule> toEntity(List<TimeCapsuleDto> timeCapsuleDtoList);
}
