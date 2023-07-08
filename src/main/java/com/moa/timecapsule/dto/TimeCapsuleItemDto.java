package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleItemDto {
	private UUID memberId;
	private UUID timeCapsuleId;
	private int decoratedItemId;
	private int size;
	private int locationX;
	private int locationY;
}
