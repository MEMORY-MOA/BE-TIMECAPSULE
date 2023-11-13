package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.*;
import com.moa.timecapsule.session.WebSessionListener;
import com.moa.timecapsule.util.Redis2Util;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.mapper.TimeCapsuleSearchMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
import com.moa.timecapsule.service.TimeCapsuleSearchService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeCapsuleSearchServiceImpl implements TimeCapsuleSearchService {
	private final MemberFeignClient memberFeignClient;
	private final TimeCapsuleSearchMapper timeCapsuleSearchMapper;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;
	private final TimeCapsuleRepository timeCapsuleRepository;

	private final Redis2Util redis2Util;
	private final WebSessionListener webSessionListener;

	@Override
	public FriendIdListDto findFriendsIdByNickname(FriendSearchDto friendSearchDto) {
		FeignFriendIdListDto feignFriendIdListDto = memberFeignClient.searchFriendsIdByNickname(
			friendSearchDto.getMemberId(), friendSearchDto.getKeyword());
		FriendIdListDto friendIdListDto = timeCapsuleSearchMapper.dtoToDto(feignFriendIdListDto);
		return friendIdListDto;
	}

	@Override
	public TimeCapsuleSearchListDto findTimeCapsuleByKeyword(UUID memberId, FriendIdListDto friendIdListDto,
															 String keyword, Pageable pageable, int page) {
		List<TimeCapsuleMember> timeCapsuleMembers = timeCapsuleMemberRepository.findByMemberId(memberId);
		List<UUID> friendIdList = friendIdListDto.getFriendIdList();
		List<UUID> timeCapsuleIdList = new ArrayList<>();
		for (TimeCapsuleMember timeCapsuleMember : timeCapsuleMembers) {
			List<TimeCapsuleMember> foundMembers = timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberIdIn(
				timeCapsuleMember.getTimeCapsuleId(),
				friendIdList);
			if (!foundMembers.isEmpty()) {
				for (TimeCapsuleMember foundMember : foundMembers) {
					timeCapsuleIdList.add(foundMember.getTimeCapsuleId());
				}
			}
		}

		Page<Timecapsule> pages = timeCapsuleRepository.findTimecapsuleByTimeCapsuleIdInOrTitleContaining(
			timeCapsuleIdList, keyword, pageable);
		List<TimeCapsuleSearchDto> timeCapsuleSearchDtoList = new ArrayList<>();
		for (Timecapsule timeCapsule : pages.getContent()) {
			timeCapsuleSearchDtoList.add(timeCapsuleSearchMapper.entityToDto(timeCapsule));
		}
		return TimeCapsuleSearchListDto.builder()
			.timeCapsulesCnt((int) pages.getTotalElements())
			.timeCapsulesPage(page)
			.timeCapsuleSearchDtoList(timeCapsuleSearchDtoList)
			.build();
	}

	@Override
	public TokenDto link(UUID memberId, UUID timeCapsuleId) {
		UUID key = UUID.randomUUID();
		System.out.println(key);
		webSessionListener.increaseSessionList(timeCapsuleId, memberId, key);

		return TokenDto.builder()
			.token(key)
			.build();
	}

	@Override
	public void unlink(UUID token, UUID timeCapsuleId) {
		webSessionListener.decreaseSessionList(timeCapsuleId, token);
	}

	@Override
	public TimeCapsuleLinkCountDto linkCount(UUID timeCapsuleId) {
		return TimeCapsuleLinkCountDto.builder()
			.count(webSessionListener.currentSessionList(timeCapsuleId))
			.build();
	}
}
