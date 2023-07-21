package com.moa.timecapsule.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleItemRegisterDto {
	private UUID memberId;
	private UUID timeCapsuleId;
	private List<TimeCapsuleItemIdTypeDto> timeCapsuleItemIdTypeDtoList;
}
