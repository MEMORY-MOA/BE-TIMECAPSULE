package com.moa.timecapsule.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.dto.FeignFriendIdListDto;
import com.moa.timecapsule.dto.FriendIdListDto;
import com.moa.timecapsule.dto.FriendSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchDto;
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

	@Override
	public FriendIdListDto findFriendsIdByNickname(FriendSearchDto friendSearchDto) {
		FeignFriendIdListDto feignFriendIdListDto = memberFeignClient.searchFriendsIdByNickname(
			friendSearchDto.getMemberId(), friendSearchDto.getKeyword());
		FriendIdListDto friendIdListDto = timeCapsuleSearchMapper.dtoToDto(feignFriendIdListDto);
		return friendIdListDto;
	}

	@Override
	public List<TimeCapsuleSearchDto> findTimeCapsuleByKeyword(UUID memberId, FriendIdListDto friendIdListDto,
		String keyword) {
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

		List<Timecapsule> timecapsuleList = timeCapsuleRepository.findTimecapsuleByTimeCapsuleIdInOrTitle(
			timeCapsuleIdList, keyword);
		List<TimeCapsuleSearchDto> timeCapsuleSearchDtoList = new ArrayList<>();
		for (Timecapsule timeCapsule : timecapsuleList) {
			timeCapsuleSearchDtoList.add(timeCapsuleSearchMapper.entityToDto(timeCapsule));
		}
		return timeCapsuleSearchDtoList;
	}
}
