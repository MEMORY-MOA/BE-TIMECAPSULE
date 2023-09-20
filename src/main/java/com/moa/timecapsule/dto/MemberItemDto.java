package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberItemDto {
	private UUID memberId;
	private Integer itemId;
}
