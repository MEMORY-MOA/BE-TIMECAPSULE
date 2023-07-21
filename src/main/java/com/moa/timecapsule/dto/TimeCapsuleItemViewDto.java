package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleItemViewDto {
	private UUID memberId;
	private UUID timeCapsuleId;
}
