package com.moa.timecapsule.controller;

import java.util.UUID;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleContentResponse;
import com.moa.timecapsule.controller.response.TimeCapsuleIdResponseDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.mapper.TimeCapsuleDtoMapper;
import com.moa.timecapsule.service.TimeCapsuleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-capsules")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleController {

	private final TimeCapsuleService timeCapsuleService;
	private final TimeCapsuleDtoMapper timeCapsuleDtoMapper;

	@PostMapping
	public ResponseDto generateTimeCapsule(@RequestBody GenerateTimeCapsuleRequest request) {
		TimeCapsuleDto timeCapsuleDto = timeCapsuleService.insertTimeCapsule(
			timeCapsuleDtoMapper.fromGenerateTimeCapsuleRequest(request));

		return ResponseDto.builder()
			.code(HttpStatus.CREATED)
			.msg("Time Capsule is Created Successful")
			.data(TimeCapsuleIdResponseDto.builder()
				.timeCapsuleId(timeCapsuleDto.getTimeCapsuleId())
				.build())
			.build();
	}

	@PostMapping("/{time-capsule}")
	public ResponseDto generateTimeCapsuleText(@PathVariable("time-capsule") UUID timeCapsuleId,
		@RequestBody GenerateTimeCapsuleContentRequest request) {
		TimeCapsuleTextDto timeCapsuleTextDto;
		System.out.println(request.getText());
		if (request.getText() != null) {
			timeCapsuleTextDto = timeCapsuleService.insertTimeCapsuleText(
				timeCapsuleDtoMapper.fromGenerateTimeCapsuleTextRequest(request, timeCapsuleId, null));

			return ResponseDto.builder()
				.code(HttpStatus.CREATED)
				.msg("Time Capsule Text is Created Successful")
				.data(TimeCapsuleContentResponse.builder()
					.content(timeCapsuleTextDto.getText())
					.type("text")
					.memberId(timeCapsuleTextDto.getMemberId())
					.build())
				.build();
		} else {
			// 이미지 등 url
		}

		return null;
	}
}
