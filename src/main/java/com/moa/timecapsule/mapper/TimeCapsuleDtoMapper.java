package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleDtoMapper {
	@Mapping(source = "memberId", target = "creator")
	TimeCapsuleDto fromGenerateTimeCapsuleRequest(UUID memberId, GenerateTimeCapsuleRequest generateTimeCapsuleRequest);

	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextDto toGenerateTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);
}
