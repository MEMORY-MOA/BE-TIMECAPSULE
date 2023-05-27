package com.moa.timecapsule.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.dto.TimeCapsuleCheckDto;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.TimeCapsuleText;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.mapper.TimeCapsuleMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
import com.moa.timecapsule.repository.TimeCapsuleTextRepository;
import com.moa.timecapsule.service.TimeCapsuleService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeCapsuleServiceImpl implements TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;
	private final TimeCapsuleTextRepository timeCapsuleTextRepository;

	private final TimeCapsuleMapper timeCapsuleMapper;

	private final MemberFeignClient memberFeignClient;

	@Override
	@Transactional
	public TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto) {
		Timecapsule timecapsule = timeCapsuleRepository.save(timeCapsuleMapper.toEntity(timeCapsuleDto));

		insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), timeCapsuleDto.getCreator());

		for (TimeCapsuleMemberDto friend : timeCapsuleDto.getFriends()) {
			insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), friend.getMemberId());
		}

		return timeCapsuleMapper.toDto(timecapsule);
	}

	@Override
	@Transactional
	public TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto) {
		checkTimeCapsuleMember(timeCapsuleTextDto.getTimeCapsuleId(), timeCapsuleTextDto.getMemberId());

		TimeCapsuleText timeCapsuleText = timeCapsuleTextRepository.save(
			timeCapsuleMapper.toEntity(timeCapsuleTextDto));

		return timeCapsuleMapper.toDto(timeCapsuleText);
	}

	@Override
	public TimeCapsuleDto selectTimeCapsule(TimeCapsuleCheckDto timeCapsuleCheckDto) {
		checkTimeCapsuleMember(timeCapsuleCheckDto.getTimeCapsuleId(), timeCapsuleCheckDto.getMemberId());

		Timecapsule timecapsule = timeCapsuleRepository.findTimecapsuleByTimeCapsuleId(
			timeCapsuleCheckDto.getTimeCapsuleId());

		List<UUID> friendsUUIDList = timeCapsuleMemberRepository.findByTimeCapsuleId(
			timeCapsuleCheckDto.getTimeCapsuleId())
			.stream()
			.map(timeCapsuleMember -> timeCapsuleMember.getMemberId())
			.toList();

		List<TimeCapsuleMemberDto> friends = memberFeignClient.getMembersInfo(friendsUUIDList);
		TimeCapsuleDto timeCapsuleDto = timeCapsuleMapper.toDto(timecapsule);
		timeCapsuleDto.insertFriends(friends);

		return timeCapsuleDto;
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
