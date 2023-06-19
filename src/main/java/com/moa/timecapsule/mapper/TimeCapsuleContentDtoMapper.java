package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleTextResponse;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentDtoMapper {
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	@Mapping(source = "timeCapsuleMemberDto.nickname", target = "nickname")
	TimeCapsuleTextResponse toTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

}
