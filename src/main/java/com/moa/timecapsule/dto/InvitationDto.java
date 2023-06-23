package com.moa.timecapsule.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
public class InvitationDto {

	private UUID timeCapsuleId;

	private LocalDateTime expiredAt;

}
