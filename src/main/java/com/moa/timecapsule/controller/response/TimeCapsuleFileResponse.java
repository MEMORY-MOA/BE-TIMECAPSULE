package com.moa.timecapsule.controller.response;

import com.moa.timecapsule.entity.ContentType;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class TimeCapsuleFileResponse {
	private final UUID timeCapsuleFileId;

	private final String fileUrl;

	private final ContentType contentType;

	private String loginId;

	private String nickname;
}
