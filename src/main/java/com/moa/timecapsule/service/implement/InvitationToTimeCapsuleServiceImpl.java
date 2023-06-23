package com.moa.timecapsule.service.implement;

import com.moa.timecapsule.dto.InvitationDto;
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

	@Override
	public InvitationDto create(UUID member, UUID timeCapsuleId) {
		LocalDateTime expiredAt = LocalDateTime.now().plusDays(1);
		redisUtil.setDataExpire(String.valueOf(timeCapsuleId), String.valueOf(expiredAt), 60 * 24L);

		return InvitationDto.builder()
			.timeCapsuleId(timeCapsuleId)
			.expiredAt(expiredAt)
			.build();
	}
}
