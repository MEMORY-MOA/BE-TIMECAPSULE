package com.moa.timecapsule.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleQueryRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
import com.moa.timecapsule.service.TimeCapsuleService;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleServiceImpl implements TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;
	private final TimeCapsuleQueryRepository timeCapsuleQueryRepository;

	private final TimeCapsuleMapper timeCapsuleMapper;

	private final MemberFeignClient memberFeignClient;

	@Override
	@Transactional
	@Synchronized
	public TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto) {
		Timecapsule timecapsule = timeCapsuleRepository.save(timeCapsuleMapper.toEntity(timeCapsuleDto));

		insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), timeCapsuleDto.getCreator());

		for (TimeCapsuleMemberDto friend : timeCapsuleDto.getFriends()) {
			insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), friend.getMemberId());
		}

		return timeCapsuleMapper.toDto(timecapsule);
	}

	@Override
	public TimeCapsuleDto selectTimeCapsule(TimeCapsuleIdsDto timeCapsuleIdsDto) {
		checkTimeCapsuleMember(timeCapsuleIdsDto.getTimeCapsuleId(), timeCapsuleIdsDto.getMemberId());

		Timecapsule timecapsule = timeCapsuleRepository.findTimecapsuleByTimeCapsuleId(
			timeCapsuleIdsDto.getTimeCapsuleId()).orElseThrow(() -> new NotFoundException("타임캡슐을 찾을 수가 없습니다."));

		List<UUID> friendsIdList = timeCapsuleMemberRepository.findByTimeCapsuleId(
			timeCapsuleIdsDto.getTimeCapsuleId())
			.stream()
			.map(timeCapsuleMember -> timeCapsuleMember.getMemberId())
			.toList();

		List<TimeCapsuleMemberDto> friends = memberFeignClient.getMembersInfo(friendsIdList);
		TimeCapsuleDto timeCapsuleDto = timeCapsuleMapper.toDto(timecapsule);
		timeCapsuleDto.setFriends(friends);

		return timeCapsuleDto;
	}

	@Override
	public List<TimeCapsuleDto> selectTimeCapsules(UUID member, Pageable page) {
		log.info(String.valueOf(member));
		log.info(String.valueOf(page.getPageSize()));
		return timeCapsuleMapper.toDto(timeCapsuleQueryRepository.findByMemberId(member, page));
	}

	private void insertTimeCapsuleMember(UUID timeCapsuleId, UUID friend) {
		timeCapsuleMemberRepository.save(TimeCapsuleMember.builder()
			.timeCapsuleId(timeCapsuleId)
			.memberId(friend)
			.build());
	}

	private void checkTimeCapsuleMember(UUID timeCapsuleId, UUID memberId) {
		timeCapsuleMemberRepository.findByTimeCapsuleIdAndMemberId(timeCapsuleId, memberId)
			.orElseThrow(() -> new NotFoundException("타임캡슐 멤버가 아닙니다."));
	}
}
