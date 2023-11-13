package com.moa.timecapsule.service;

import java.util.UUID;

import com.moa.timecapsule.dto.*;
import org.springframework.data.domain.Pageable;

public interface TimeCapsuleSearchService {
	FriendIdListDto findFriendsIdByNickname(FriendSearchDto friendSearchDto);

	TimeCapsuleSearchListDto findTimeCapsuleByKeyword(UUID memberId, FriendIdListDto friendIdListDto,
		String keyword, Pageable pageable, int page);

	TokenDto link(UUID member, UUID timeCapsuleId);

	TimeCapsuleLinkCountDto linkCount(UUID timeCapsuleId);

	void unlink(UUID token, UUID timeCapsuleId);
}
