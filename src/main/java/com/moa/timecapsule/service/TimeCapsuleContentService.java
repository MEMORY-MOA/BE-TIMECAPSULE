package com.moa.timecapsule.service;

import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleTextDto;

public interface TimeCapsuleContentService {
	List<TimeCapsuleTextDto> selectTimeCapsuleTextList(UUID timeCapsuleId);

	TimeCapsuleTextDto insertTimeCapsuleText(TimeCapsuleTextDto timeCapsuleTextDto);

	TimeCapsuleTextDto selectTimeCapsuleText(UUID timeCapsuleTextId);

}
