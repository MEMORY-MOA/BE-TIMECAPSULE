package com.moa.timecapsule.service.implement;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.mapper.TimeCapsuleMapper;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
import com.moa.timecapsule.service.TimeCapsuleService;

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
	public TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto) {
		Timecapsule timecapsule = timeCapsuleRepository.save(timeCapsuleMapper.toEntity(timeCapsuleDto));

		for (Object friend : timeCapsuleDto.getFriends()) {
			insertTimeCapsuleMember(timecapsule.getTimeCapsuleId(), (UUID)friend);
		}

		return timeCapsuleMapper.toDto(timecapsule);
	}

	@Override
	public TimeCapsuleDto selectTimeCapsule(UUID timeCapsuleId) {
		Timecapsule timecapsule = timeCapsuleRepository.findTimecapsuleByTimeCapsuleId(timeCapsuleId);

		// feign client로 친구 값 가져오기
		//TimeCapsuleMemberNicknameDto friends = new TimeCapsuleMemberNicknameDto();
		TimeCapsuleDto timeCapsuleDto = timeCapsuleMapper.toDto(timecapsule);
		//timeCapsuleDto.insertFriends(friends);
		return timeCapsuleDto;
	}

	private void insertTimeCapsuleMember(UUID timeCapsuleId, UUID friend) {
		timeCapsuleMemberRepository.save(TimeCapsuleMember.builder()
			.timeCapsuleId(timeCapsuleId)
			.memberId(friend)
			.build());
	}

}
