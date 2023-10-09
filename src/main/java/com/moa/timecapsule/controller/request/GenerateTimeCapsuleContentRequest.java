package com.moa.timecapsule.controller.request;

import lombok.Getter;
import lombok.Setter;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GenerateTimeCapsuleContentRequest {
	private UUID timecapsuleId;
	private List<GenerateTimeCapsuleTextRequest> text;
	private List<MultipartFile> multipartFiles;
}
