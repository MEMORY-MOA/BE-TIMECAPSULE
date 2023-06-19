package com.moa.timecapsule.controller.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleTextResponse {
	private UUID timeCapsuleTextId;

	private String text;

	private String nickname;
}
