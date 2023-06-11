package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleTextResponse;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentDtoMapper {
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextResponse toTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

}
