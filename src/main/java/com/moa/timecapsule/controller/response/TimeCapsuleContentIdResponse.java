package com.moa.timecapsule.controller.response;

import java.util.UUID;

import com.moa.timecapsule.entity.ContentType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleContentIdResponse {
	private UUID contentId;

	@Enumerated(EnumType.STRING)
	private ContentType contentType;
}
