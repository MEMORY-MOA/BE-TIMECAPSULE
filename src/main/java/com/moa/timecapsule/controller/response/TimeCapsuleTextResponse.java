package com.moa.timecapsule.controller.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleTextResponse {
	private UUID timeCapsuleTextId;

	private String text;

	private String color;

	private String loginId;

	private String nickname;

	private LocalDateTime createdAt;
}
