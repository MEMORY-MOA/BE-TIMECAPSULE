package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.TimeCapsuleItemRequest;
import com.moa.timecapsule.dto.TimeCapsuleItemDto;
import com.moa.timecapsule.entity.TimeCapsuleItem;

@Mapper(componentModel = "spring")
public interface TimeCapsuleItemMapper {
	TimeCapsuleItemDto toDto(UUID memberId, UUID timeCapsuleId, TimeCapsuleItemRequest timeCapsuleItemRequest);

	TimeCapsuleItem dtoToEntity(TimeCapsuleItemDto timeCapsuleItemDto);
}
