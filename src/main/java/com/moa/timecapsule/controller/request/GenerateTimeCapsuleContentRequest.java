package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@Data
public class GenerateTimeCapsuleContentRequest {
	private List<GenerateTimeCapsuleTextRequest> textList;
}
