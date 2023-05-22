package com.moa.timecapsule.mapper;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.dto.TimeCapsuleDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleDtoMapper {
	TimeCapsuleDto fromGenerateTimeCapsuleRequest(GenerateTimeCapsuleRequest generateTimeCapsuleRequest);
}
