package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleContentResponse;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleDtoMapper {
	TimeCapsuleDto fromGenerateTimeCapsuleRequest(GenerateTimeCapsuleRequest generateTimeCapsuleRequest);
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(GenerateTimeCapsuleContentRequest request, UUID timeCapsuleId, UUID memberId);
}
