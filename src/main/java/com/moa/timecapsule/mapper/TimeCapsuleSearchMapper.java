package com.moa.timecapsule.mapper;

import java.util.UUID;

import com.moa.timecapsule.controller.response.TimeCapsuleLinkCountResponse;
import com.moa.timecapsule.dto.*;
import org.mapstruct.Mapper;

import com.moa.timecapsule.controller.response.TimeCapsuleSearchResponse;
import com.moa.timecapsule.entity.Timecapsule;

@Mapper(componentModel = "spring")
public interface TimeCapsuleSearchMapper {
	FriendSearchDto toDto(UUID memberId, String keyword);

	FriendIdListDto dtoToDto(FeignFriendIdListDto feignFriendIdListDto);

	TimeCapsuleSearchDto entityToDto(Timecapsule timeCapsule);

	TimeCapsuleSearchResponse dtoToResponse(TimeCapsuleSearchListDto timecapsuleSearchListDto);

    TokenDto dtoToTokenResponse(TokenDto tokenDto);

	TimeCapsuleLinkCountResponse dtoToTimeCapsuleLinkCountResponse(TimeCapsuleLinkCountDto timeCapsuleLinkCountDto);
}
