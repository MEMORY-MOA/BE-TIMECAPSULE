package com.moa.timecapsule.controller.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeCapsuleIdResponseDto {
	private UUID timeCapsuleId;
}
