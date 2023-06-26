package com.moa.timecapsule.service.implement;

import com.moa.timecapsule.dto.InvitationDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.service.InvitationToTimeCapsuleService;
import com.moa.timecapsule.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvitationToTimeCapsuleServiceImpl implements InvitationToTimeCapsuleService {

	private final RedisUtil redisUtil;

	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;

	@Override
	public InvitationDto create(UUID member, UUID timeCapsuleId) {
		LocalDateTime expiredAt = LocalDateTime.now().plusDays(1);
		redisUtil.setDataExpire(String.valueOf(timeCapsuleId), String.valueOf(expiredAt), 60 * 24L);

		return InvitationDto.builder()
			.timeCapsuleId(timeCapsuleId)
			.expiredAt(expiredAt)
			.build();
	}

	@Override
	public void accept(UUID member, UUID timeCapsuleId) {
		// feign client로 회원 및 수락


		timeCapsuleMemberRepository.save(
			TimeCapsuleMember.builder()
				.timeCapsuleId(timeCapsuleId)
				.memberId(member)
				.build());
	}
}
