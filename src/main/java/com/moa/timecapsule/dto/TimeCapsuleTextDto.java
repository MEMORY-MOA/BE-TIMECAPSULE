package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TimeCapsuleTextDto extends BaseDto {

	private UUID timeCapsuleTextId;

	private UUID timeCapsuleId;

	private UUID memberId;

	private String text;

	private TimeCapsuleMemberDto timeCapsuleMemberDto;
}
