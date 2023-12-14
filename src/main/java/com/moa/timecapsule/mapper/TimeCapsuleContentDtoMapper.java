package com.moa.timecapsule.mapper;

import java.util.UUID;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleTextRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleFileResponse;
import com.moa.timecapsule.dto.TimeCapsuleFileDto;
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
	@Mapping(source = "timeCapsuleMemberDto.loginId", target = "loginId")
	@Mapping(source = "timeCapsuleMemberDto.memColor", target = "memColor")
	TimeCapsuleTextResponse toTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

	@Mapping(source = "timeCapsuleMemberDto.nickname", target = "nickname")
	@Mapping(source = "timeCapsuleMemberDto.loginId", target = "loginId")
	@Mapping(source = "timeCapsuleMemberDto.memColor", target = "memColor")
	TimeCapsuleFileResponse toTimeCapsuleFileResponse(TimeCapsuleFileDto timeCapsuleFileDto);
}
