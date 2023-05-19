package com.moa.timecapsule.controller;

import com.moa.timecapsule.controller.request.generateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleIdResponseDto;
import com.moa.timecapsule.dto.TimeCapsuleDto;
import com.moa.timecapsule.mapper.TimeCapsuleDtoMapper;
import com.moa.timecapsule.service.TimeCapsuleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-capsule")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleController {

	private final TimeCapsuleService timeCapsuleService;
	private final TimeCapsuleDtoMapper timeCapsuleDtoMapper;

	@PostMapping
	public ResponseDto generateTimeCapsule(@RequestBody generateTimeCapsuleRequest request) {
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
}
