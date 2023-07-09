package com.moa.timecapsule.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleResponse;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;

@Mapper(componentModel = "spring")
public interface TimeCapsuleDtoMapper {
	@Mapping(source = "memberId", target = "creator")
	@Mapping(target = "friends", expression = "java(uuidToTimeCapsuleDto(generateTimeCapsuleRequest.getFriends()))")
	TimeCapsuleDto fromGenerateTimeCapsuleRequest(UUID memberId, GenerateTimeCapsuleRequest generateTimeCapsuleRequest);

	TimeCapsuleIdsDto toTimeCapsuleIdsDto(UUID memberId, UUID timeCapsuleId);

	TimeCapsuleResponse toTimeCapsuleResponse(TimeCapsuleDto timeCapsuleDto);

	List<TimeCapsuleResponse> toTimeCapsuleResponse(List<TimeCapsuleDto> timeCapsuleDtoList);

	default List<TimeCapsuleMemberDto> uuidToTimeCapsuleDto(List<UUID> friends) {
		List<TimeCapsuleMemberDto> list = new ArrayList<>();

		for (UUID friend : friends) {
			list.add(TimeCapsuleMemberDto.builder().memberId(friend).build());
		}

		return list;
	}
}
