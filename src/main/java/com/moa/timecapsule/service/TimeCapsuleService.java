package com.moa.timecapsule.service;

import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleListDto;
import org.springframework.data.domain.Pageable;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleIdsDto;

public interface TimeCapsuleService {
	TimeCapsuleDto insertTimeCapsule(TimeCapsuleDto timeCapsuleDto);

	TimeCapsuleDto selectTimeCapsule(TimeCapsuleIdsDto timeCapsuleIdsDto);

	TimeCapsuleListDto selectTimeCapsules(UUID member, Pageable page);

	void updateIsOpened(UUID timeCapsuleId);
}
