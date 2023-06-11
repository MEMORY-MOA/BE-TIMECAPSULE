package com.moa.timecapsule.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleText;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentMapper {
	TimeCapsuleTextDto toDto(TimeCapsuleText timeCapsuleText);

	TimeCapsuleText toEntity(TimeCapsuleTextDto timeCapsuleTextDto);

	List<TimeCapsuleTextDto> toDto(List<TimeCapsuleText> timeCapsuleTextList);
}
