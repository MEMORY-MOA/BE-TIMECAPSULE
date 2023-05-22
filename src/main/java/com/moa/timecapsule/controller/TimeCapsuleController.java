package com.moa.timecapsule.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleIdResponse;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.mapper.TimeCapsuleDtoMapper;
import com.moa.timecapsule.service.TimeCapsuleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleController {

	private final TimeCapsuleService timeCapsuleService;
	private final TimeCapsuleDtoMapper timeCapsuleDtoMapper;

	@PostMapping
	public ResponseEntity<ResponseDto> generateTimeCapsule(@RequestHeader("member") UUID member,
		@RequestBody GenerateTimeCapsuleRequest request) {
		TimeCapsuleDto timeCapsuleDto = timeCapsuleService.insertTimeCapsule(
			timeCapsuleDtoMapper.fromGenerateTimeCapsuleRequest(member, request));

		return ResponseEntity.ok(ResponseDto.builder()
			.code(HttpStatus.OK)
			.msg("타임캡슐이 생성되었습니다.")
			.data(TimeCapsuleIdResponse.builder()
				.timeCapsuleId(timeCapsuleDto.getTimeCapsuleId())
				.build())
			.build());
	}

	@PostMapping("/{time-capsule}/text")
	public ResponseEntity<ResponseDto> generateTimeCapsuleText(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId,
		@RequestBody GenerateTimeCapsuleContentRequest request) {
		TimeCapsuleTextDto timeCapsuleTextDto = timeCapsuleService.insertTimeCapsuleText(
			timeCapsuleDtoMapper.fromGenerateTimeCapsuleTextRequest(member, timeCapsuleId, request));

		return ResponseEntity.ok(ResponseDto.builder()
			.code(HttpStatus.OK)
			.msg("편지가 타임캡슐에 담겼습니다.")
			.data(timeCapsuleDtoMapper.toGenerateTimeCapsuleTextResponse(timeCapsuleTextDto))
			.build());
	}
}
