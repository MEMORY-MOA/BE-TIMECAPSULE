package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TimeCapsuleMemberNicknameDto {

	private UUID memberId;

	private String nickname;
}
