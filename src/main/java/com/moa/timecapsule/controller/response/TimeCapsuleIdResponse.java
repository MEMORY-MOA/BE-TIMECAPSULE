package com.moa.timecapsule.controller.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleIdResponse {
	private UUID timeCapsuleId;
}
