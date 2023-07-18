package com.moa.timecapsule.service;

import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;

public interface TimeCapsuleItemService {
	void insertTimeCapsuleItem(TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto);

	TimeCapsuleItemListDto findTimeCapsuleItem(TimeCapsuleItemViewDto timeCapsuleItemViewDto);
}
