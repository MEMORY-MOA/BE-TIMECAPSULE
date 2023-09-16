package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.TimeCapsuleItemRequest;
import com.moa.timecapsule.controller.response.TimeCapsuleItemListResponse;
import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.TimeCapsuleItems;
import com.moa.timecapsule.mapper.custommapper.CustomTimeCapsuleItemMapperImpl;

@Mapper(componentModel = "spring")
public interface TimeCapsuleItemMapper {
	TimeCapsuleItemRegisterDto toDto(UUID memberId, UUID timeCapsuleId, TimeCapsuleItemRequest request);

	TimeCapsuleItems toEntity(UUID timeCapsuleId, TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto);

	TimeCapsuleItemViewDto toDto(UUID memberId, UUID timeCapsuleId);

	ItemDto toDto(Item item);

	TimeCapsuleItemListResponse toResponse(TimeCapsuleItemListDto timeCapsuleItemListDto);

	default TimeCapsuleItems updateMemberEntityFromTimeCapsuleItemRegisterDto(TimeCapsuleItems existingTimeCapsuleItems,
		TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto) {
		CustomTimeCapsuleItemMapperImpl customMapperImpl = new CustomTimeCapsuleItemMapperImpl();
		return customMapperImpl.updateMemberEntityFromMyPageDto(existingTimeCapsuleItems, timeCapsuleItemRegisterDto);
	}
}
