package com.moa.timecapsule.controller.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
public class GenerateTimeCapsuleContentRequest {
	private UUID timecapsuleId;
	private List<GenerateTimeCapsuleTextRequest> text;
}
