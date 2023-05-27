package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TimeCapsuleMemberDto extends BaseDto {

	private UUID memberUUID;

	private String memberId;

	private String nickname;
}
