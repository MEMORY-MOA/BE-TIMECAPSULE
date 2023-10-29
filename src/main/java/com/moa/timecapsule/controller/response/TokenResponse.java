package com.moa.timecapsule.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TokenResponse {
	private UUID token;
}
