package com.moa.timecapsule.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TokenDto {
	private UUID token;
}
