package com.moa.timecapsule.service.implement;

import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.mapper.TimeCapsuleMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
import com.moa.timecapsule.service.TimeCapsuleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TimeCapsuleServiceImpl implements TimeCapsuleService {

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;

	private final TimeCapsuleMapper timeCapsuleMapper;

	@Override
	@Transactional
	/*
		timecapsule 생성
		timecapsule member 생성
	 */
	public TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto) {
		Timecapsule timecapsule = timeCapsuleRepository.save(Timecapsule.builder()
									.title(timeCapsuleDto.getTitle())
									.closedAt(timeCapsuleDto.getClosedAt())
									.openedAt(timeCapsuleDto.getOpenedAt())
									.member(timeCapsuleDto.getMember())
									.build()
		);

		for (UUID friend: timeCapsuleDto.getFriends()) {
			insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), friend);
		}

		return timeCapsuleMapper.toDto(timecapsule);
	}

	private void insertTimeCapsuleMember(UUID timeCapsuleId, UUID friend) {
		timeCapsuleMemberRepository.save(TimeCapsuleMember.builder()
			.timeCapsuleId(timeCapsuleId)
			.memberId(friend)
			.build());
	}

}
