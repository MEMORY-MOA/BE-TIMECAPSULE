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
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.mapper.TimeCapsuleContentDtoMapper;
import com.moa.timecapsule.service.TimeCapsuleContentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules/{time-capsule}")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleContentController {

	private final TimeCapsuleContentService timeCapsuleContentService;
	private final TimeCapsuleContentDtoMapper timeCapsuleContentDtoMapper;

	@PostMapping("/text")
	public ResponseEntity<ResponseDto> generateTimeCapsuleText(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId,
		@RequestBody GenerateTimeCapsuleContentRequest request) {
		TimeCapsuleTextDto timeCapsuleTextDto = timeCapsuleContentService.insertTimeCapsuleText(
			timeCapsuleContentDtoMapper.fromGenerateTimeCapsuleTextRequest(member, timeCapsuleId, request));

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("편지가 타임캡슐에 담겼습니다.")
				.data(timeCapsuleContentDtoMapper.toGenerateTimeCapsuleTextResponse(timeCapsuleTextDto))
				.build());
	}
}
