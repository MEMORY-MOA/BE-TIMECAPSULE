package com.moa.timecapsule.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.listener.WebSessionListener;
import com.moa.timecapsule.service.InsideTimeCapsuleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules")
@RequiredArgsConstructor
@Slf4j
public class InsideTimeCapsuleController {

	private final InsideTimeCapsuleService insideTimeCapsuleService;

	@GetMapping("/{time-capsule}/users/count")
	public ResponseEntity<ResponseDto> getCurrentUsers(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId) {

		WebSessionListener webSessionListener = WebSessionListener.getInstance();
		webSessionListener.currentSessionList();

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("현재 입장 인원")
				.build());
	}
}
