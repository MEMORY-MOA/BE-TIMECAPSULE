package com.moa.timecapsule.service;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;

public interface TimeCapsuleService {
	TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto);

	TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto);

	TimeCapsuleDto selectTimeCapsule(TimeCapsuleIdsDto timeCapsuleIdsDto);
}
