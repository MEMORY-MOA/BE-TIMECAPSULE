package com.moa.timecapsule.controller.response;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleContentResponse {

	private UUID memberId;

	private String content;

	private String type;
}
