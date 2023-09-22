package com.moa.timecapsule.dto;

import java.util.UUID;

import com.moa.timecapsule.entity.ContentType;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TimeCapsuleFileDto {
	private final UUID timeCapsuleFileId;
	private UUID timeCapsuleId;
	private UUID memberId;
	private final String fileUrl;
	private final ContentType contentType;
	private TimeCapsuleMemberDto timeCapsuleMemberDto;

}
