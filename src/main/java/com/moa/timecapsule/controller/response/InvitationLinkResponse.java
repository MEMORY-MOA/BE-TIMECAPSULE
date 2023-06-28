package com.moa.timecapsule.controller.response;

import com.moa.timecapsule.dto.TimeCapsuleMemberDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InvitationLinkResponse {

	private UUID timeCapsuleId;

	private LocalDateTime expiredAt;

	private String title;

	private TimeCapsuleMemberDto timeCapsuleMemberDto;
}
