package com.moa.timecapsule.mapper;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.dto.TimeCapsuleItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemIdTypeDto;
import com.moa.timecapsule.entity.ItemType;
import com.moa.timecapsule.entity.TimeCapsuleItem;

@Mapper(componentModel = "spring")
public interface TimeCapsuleItemMapper {
	TimeCapsuleItemDto toDto(UUID memberId, UUID timeCapsuleId,
		List<TimeCapsuleItemIdTypeDto> timeCapsuleItemIdTypeDtoList);

	TimeCapsuleItemIdTypeDto toDto(Integer itemId, ItemType itemType);

	TimeCapsuleItem toEntity(UUID timeCapsuleId, Integer itemId);

}
