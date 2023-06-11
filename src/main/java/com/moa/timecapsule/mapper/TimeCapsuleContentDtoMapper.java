package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.response.GetOneTimeCapsuleTextResponse;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentDtoMapper {
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextDto toGenerateTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

	GetOneTimeCapsuleTextResponse toGetOneTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);
}
