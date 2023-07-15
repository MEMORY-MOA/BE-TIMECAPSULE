package com.moa.timecapsule.mapper;

import java.util.List;
import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.response.TimeCapsuleItemListResponse;
import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemIdTypeDto;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.ItemType;
import com.moa.timecapsule.entity.TimeCapsuleItem;

@Mapper(componentModel = "spring")
public interface TimeCapsuleItemMapper {
	TimeCapsuleItemRegisterDto toDto(UUID memberId, UUID timeCapsuleId,
		List<TimeCapsuleItemIdTypeDto> timeCapsuleItemIdTypeDtoList);

	TimeCapsuleItemIdTypeDto toDto(Integer itemId, ItemType itemType);

	TimeCapsuleItem toEntity(UUID timeCapsuleId, Integer itemId);

	TimeCapsuleItemViewDto toDto(UUID memberId, UUID timeCapsuleId);

	ItemDto toDto(Item item);

	TimeCapsuleItemListResponse toResponse(TimeCapsuleItemListDto timeCapsuleItemListDto);

}
