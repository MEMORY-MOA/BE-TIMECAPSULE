package com.moa.timecapsule.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.GetOneTimeCapsuleResponse;
import com.moa.timecapsule.dto.TimeCapsuleCheckDto;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleDtoMapper {
	@Mapping(source = "memberId", target = "creator")
	@Mapping(target = "friends", expression = "java(uuidToTimeCapsuleDto(generateTimeCapsuleRequest.getFriends()))")
	TimeCapsuleDto fromGenerateTimeCapsuleRequest(UUID memberId, GenerateTimeCapsuleRequest generateTimeCapsuleRequest);

	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextDto fromGenerateTimeCapsuleTextRequest(
		UUID memberId, UUID timeCapsuleId,
		GenerateTimeCapsuleContentRequest generateTimeCapsuleContentRequest);

	TimeCapsuleTextDto toGenerateTimeCapsuleTextResponse(TimeCapsuleTextDto timeCapsuleTextDto);

	TimeCapsuleCheckDto toTimeCapsuleCheckDto(UUID memberId, UUID timeCapsuleId);

	GetOneTimeCapsuleResponse toGetOneTimeCapsuleResponse(TimeCapsuleDto timeCapsuleDto);

	default List<TimeCapsuleMemberDto> uuidToTimeCapsuleDto(List<UUID> friends) {
		List<TimeCapsuleMemberDto> list = new ArrayList<>();

		for (UUID friend : friends) {
			list.add(TimeCapsuleMemberDto.builder().memberUUID(friend).build());
		}

		return list;
	}
}
