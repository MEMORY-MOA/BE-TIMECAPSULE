package com.moa.timecapsule.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleFileDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleFile;
import com.moa.timecapsule.entity.TimeCapsuleText;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentMapper {
	TimeCapsuleTextDto toDto(TimeCapsuleText timeCapsuleText);

	TimeCapsuleText toEntity(TimeCapsuleTextDto timeCapsuleTextDto);

	List<TimeCapsuleTextDto> toDto(List<TimeCapsuleText> timeCapsuleTextList);

	TimeCapsuleFileDto toDto(TimeCapsuleFile timeCapsuleFile);

	TimeCapsuleFile toEntity(TimeCapsuleFileDto timeCapsuleFileDto);

	List<TimeCapsuleFileDto> toDtoList(List<TimeCapsuleFile> timeCapsuleFileList);

}
