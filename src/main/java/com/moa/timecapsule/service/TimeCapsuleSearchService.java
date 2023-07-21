package com.moa.timecapsule.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.moa.timecapsule.dto.FriendIdListDto;
import com.moa.timecapsule.dto.FriendSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchListDto;

public interface TimeCapsuleSearchService {
	FriendIdListDto findFriendsIdByNickname(FriendSearchDto friendSearchDto);

	TimeCapsuleSearchListDto findTimeCapsuleByKeyword(UUID memberId, FriendIdListDto friendIdListDto,
		String keyword, Pageable pageable, int page);
}
