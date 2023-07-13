package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.request.MemberItemRequest;
import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.entity.MemberItem;

@Mapper(componentModel = "spring")
public interface MemberItemMapper {
	MemberItemDto toDto(UUID memberId, MemberItemRequest memberItemRequest);

	MemberItem dtoToEntity(MemberItemDto memberItemDto);

}

