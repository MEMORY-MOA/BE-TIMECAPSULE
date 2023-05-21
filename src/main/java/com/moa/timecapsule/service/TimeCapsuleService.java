package com.moa.timecapsule.service;

import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleDto;

public interface TimeCapsuleService {
	TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto);
	TimeCapsuleDto selectTimeCapsule(UUID timeCapsuleId);
}
