package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleItemRegisterDto {
	private UUID memberId;
	private UUID timeCapsuleId;
	private Integer boxShapeItemId;
	private Integer effectItemId;
	private Integer lockShapeItemId;
}
