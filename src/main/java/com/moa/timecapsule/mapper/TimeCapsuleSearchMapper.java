package com.moa.timecapsule.mapper;

import java.util.UUID;

import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.response.TimeCapsuleSearchResponse;
import com.moa.timecapsule.dto.FeignFriendIdListDto;
import com.moa.timecapsule.dto.FriendIdListDto;
import com.moa.timecapsule.dto.FriendSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchListDto;
import com.moa.timecapsule.entity.Timecapsule;

@Mapper(componentModel = "spring")
public interface TimeCapsuleSearchMapper {
	FriendSearchDto toDto(UUID memberId, String keyword);

	FriendIdListDto dtoToDto(FeignFriendIdListDto feignFriendIdListDto);

	TimeCapsuleSearchDto entityToDto(Timecapsule timeCapsule);

	TimeCapsuleSearchResponse dtoToResponse(TimeCapsuleSearchListDto timecapsuleSearchListDto);
}
