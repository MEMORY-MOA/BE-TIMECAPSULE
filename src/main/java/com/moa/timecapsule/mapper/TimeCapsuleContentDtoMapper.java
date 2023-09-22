package com.moa.timecapsule.mapper;

import java.util.UUID;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleTextRequest;
import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.response.TimeCapsuleTextResponse;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeCapsuleContentDtoMapper {
	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleTextRequest generateTimeCapsuleTextRequest);

	@Mapping(source = "timeCapsuleMemberDto.nickname", target = "nickname")
	TimeCapsuleTextResponse toTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

}
