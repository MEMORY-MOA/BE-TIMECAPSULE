package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.MemberItemViewRequest;
import com.moa.timecapsule.controller.response.MemberItemListResponse;
import com.moa.timecapsule.dto.ItemDto;
import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemListDto;
import com.moa.timecapsule.dto.MemberItemViewDto;
import com.moa.timecapsule.entity.Item;
import com.moa.timecapsule.entity.MemberItem;

@Mapper(componentModel = "spring")
public interface MemberItemMapper {
	MemberItemDto toDto(UUID memberId, int itemId);

	MemberItem dtoToEntity(MemberItemDto memberItemDto);

	MemberItemListResponse dtoToResponse(MemberItemListDto memberItemIdsDto);

	MemberItemViewDto toDto(UUID memberId, MemberItemViewRequest memberItemViewRequest);

	ItemDto toDto(Item item);

}

