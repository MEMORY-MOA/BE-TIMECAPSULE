package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TimeCapsuleTextDto extends BaseDto {

	private UUID timeCapsuleTextId;

	private UUID timeCapsuleId;

	private UUID memberId;

	private String text;
}
