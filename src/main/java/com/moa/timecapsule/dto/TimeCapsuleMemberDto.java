package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TimeCapsuleMemberDto {

	private UUID memberId;

	private String loginId;

	private String nickname;

	private int memColor;
}
