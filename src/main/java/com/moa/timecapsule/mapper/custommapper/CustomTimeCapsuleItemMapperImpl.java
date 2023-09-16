package com.moa.timecapsule.mapper.custommapper;

import org.springframework.stereotype.Component;

import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.entity.TimeCapsuleItems;

@Component
public class CustomTimeCapsuleItemMapperImpl {
	public TimeCapsuleItems updateMemberEntityFromMyPageDto(TimeCapsuleItems existingTimeCapsuleItems,
		TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto) {
		if (timeCapsuleItemRegisterDto == null) {
			return null;
		}

		TimeCapsuleItems.TimeCapsuleItemsBuilder<?, ?> timeCapsuleItems = TimeCapsuleItems.builder();

		timeCapsuleItems.timeCapsuleId(existingTimeCapsuleItems.getTimeCapsuleId());

		if (timeCapsuleItemRegisterDto.getBoxShapeItemId() == null) {
			timeCapsuleItems.boxShapeItemId(existingTimeCapsuleItems.getBoxShapeItemId());
		} else {
			timeCapsuleItems.boxShapeItemId(timeCapsuleItemRegisterDto.getBoxShapeItemId());
		}
		if (timeCapsuleItemRegisterDto.getEffectItemId() == null) {
			timeCapsuleItems.effectItemId(existingTimeCapsuleItems.getEffectItemId());
		} else {
			timeCapsuleItems.effectItemId(timeCapsuleItemRegisterDto.getEffectItemId());
		}
		if (timeCapsuleItemRegisterDto.getLockShapeItemId() == null) {
			timeCapsuleItems.lockShapeItemId(existingTimeCapsuleItems.getLockShapeItemId());
		} else {
			timeCapsuleItems.lockShapeItemId(timeCapsuleItemRegisterDto.getLockShapeItemId());
		}
		return timeCapsuleItems.build();
	}
}
