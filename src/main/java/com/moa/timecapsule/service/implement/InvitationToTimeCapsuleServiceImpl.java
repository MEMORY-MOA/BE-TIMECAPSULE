package com.moa.timecapsule.service.implement;

import com.moa.timecapsule.client.MemberFeignClient;
import com.moa.timecapsule.dto.InvitationDto;
import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import com.moa.timecapsule.entity.TimeCapsuleMember;
import com.moa.timecapsule.entity.Timecapsule;
import com.moa.timecapsule.exception.NotFoundException;
import com.moa.timecapsule.repository.TimeCapsuleMemberRepository;
import com.moa.timecapsule.repository.TimeCapsuleRepository;
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

	private final TimeCapsuleRepository timeCapsuleRepository;
	private final TimeCapsuleMemberRepository timeCapsuleMemberRepository;

	private final MemberFeignClient memberFeignClient;

	@Override
	public InvitationDto create(UUID member, UUID timeCapsuleId) {
		LocalDateTime expiredAt = LocalDateTime.now().plusDays(1);
		redisUtil.setDataExpire(String.valueOf(timeCapsuleId), String.valueOf(expiredAt), 60 * 24L);

		Timecapsule timecapsule = timeCapsuleRepository.findTimecapsuleByTimeCapsuleId(timeCapsuleId)
			.orElseThrow(() -> new NotFoundException("타임캡슐을 찾을 수가 없습니다."));

		TimeCapsuleMemberDto timeCapsuleMemberDto = memberFeignClient.getMemberInfo(member);

		return InvitationDto.builder()
			.timeCapsuleId(timeCapsuleId)
			.title(timecapsule.getTitle())
			.expiredAt(expiredAt)
			.timeCapsuleMemberDto(timeCapsuleMemberDto)
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
