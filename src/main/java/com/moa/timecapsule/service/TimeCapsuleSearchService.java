package com.moa.timecapsule.service;

import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.FriendIdListDto;
import com.moa.timecapsule.dto.FriendSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchDto;

public interface TimeCapsuleSearchService {
	FriendIdListDto findFriendsIdByNickname(FriendSearchDto friendSearchDto);

	public List<TimeCapsuleSearchDto> findTimeCapsuleByKeyword(UUID memberId, FriendIdListDto friendIdListDto,
		String keyword);
}
