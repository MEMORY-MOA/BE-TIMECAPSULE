package com.moa.timecapsule.controller.request;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class GenerateTimeCapsuleContentRequest {
	private List<GenerateTimeCapsuleTextRequest> text;

	private List<MultipartFile> multipartFiles;

}
