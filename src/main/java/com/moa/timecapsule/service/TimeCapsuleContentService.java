package com.moa.timecapsule.service;

import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;

public interface TimeCapsuleContentService {
	TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto);

	TimeCapsuleTextDto selectTimeCapsuleText(UUID timeCapsuleTextId);
}
