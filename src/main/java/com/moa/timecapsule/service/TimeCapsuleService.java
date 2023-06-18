package com.moa.timecapsule.service;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;

public interface TimeCapsuleService {
	TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto);

	TimeCapsuleDto selectTimeCapsule(TimeCapsuleIdsDto timeCapsuleIdsDto);
}
