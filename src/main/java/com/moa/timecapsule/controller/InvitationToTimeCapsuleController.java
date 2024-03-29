package com.moa.timecapsule.controller;

import com.moa.timecapsule.controller.response.InvitationLinkResponse;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.dto.InvitationDto;
import com.moa.timecapsule.mapper.InvitationToTimeCapsuleDtoMapper;
import com.moa.timecapsule.service.InvitationToTimeCapsuleService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/time-capsules/{time-capsule}/invitation")
@RequiredArgsConstructor
@Slf4j
public class InvitationToTimeCapsuleController {

	private final InvitationToTimeCapsuleService invitationToTimeCapsuleService;

	private final InvitationToTimeCapsuleDtoMapper invitationToTimeCapsuleDtoMapper;

	@GetMapping
	@Operation(summary = "타임캡슐 초대 API_yejin")
	public ResponseEntity<ResponseDto> createLink(@RequestHeader("member") UUID member,
												  @PathVariable("time-capsule") UUID timeCapsuleId) {
		InvitationDto invitationDto = invitationToTimeCapsuleService.create(member, timeCapsuleId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("초대 정보가 생성되었습니다.")
				.data(invitationToTimeCapsuleDtoMapper.toInvitationLinkResponse(invitationDto))
				.build());
	}

	@GetMapping("/acceptance")
	@Operation(summary = "타임캡슐 초대 수락 API_yejin")
	public ResponseEntity<ResponseDto> accept(@RequestHeader("member") UUID member,
											  @PathVariable("time-capsule") UUID timeCapsuleId) {
		invitationToTimeCapsuleService.accept(member, timeCapsuleId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("초대를 수락하셨습니다.")
				.build());
	}
}
