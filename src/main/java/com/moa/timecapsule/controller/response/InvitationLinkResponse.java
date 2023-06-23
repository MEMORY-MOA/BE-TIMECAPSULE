package com.moa.timecapsule.controller.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InvitationLinkResponse {

	private UUID timeCapsuleId;

	private LocalDateTime expiredAt;
}
