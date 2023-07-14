package com.moa.timecapsule.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.request.MemberItemRequest;
import com.moa.timecapsule.controller.request.MemberItemViewRequest;
import com.moa.timecapsule.controller.response.MemberItemIdsResponse;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemIdsDto;
import com.moa.timecapsule.dto.MemberItemViewDto;
import com.moa.timecapsule.mapper.MemberItemMapper;
import com.moa.timecapsule.service.MemberItemService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules/user-item")
@RequiredArgsConstructor
@Slf4j
public class MemberItemController {
	private final MemberItemMapper memberItemMapper;
	private final MemberItemService memberItemService;

	@PostMapping
	public ResponseEntity<ResponseDto> registerMemberItem(@RequestHeader("memberId") UUID memberId,
		@RequestBody MemberItemRequest request) {
		MemberItemDto memberItemDto = memberItemMapper.toDto(memberId, request);
		memberItemService.insertMemberItem(memberItemDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("사용자 타임캡슐 아이템이 등록되었습니다.")
				.build());
	}

	@GetMapping
	public ResponseEntity<ResponseDto> viewTimeCapsuleItem(@RequestHeader("memberId") UUID memberId,
		@RequestBody MemberItemViewRequest request) {
		MemberItemViewDto memberItemViewDto = memberItemMapper.toDto(memberId, request);
		MemberItemIdsDto memberItemIdsDto = memberItemService.findMemberItem(memberItemViewDto);
		MemberItemIdsResponse memberItemIdsResponse = memberItemMapper.dtoToResponse(memberItemIdsDto);
		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("사용자 아이템이 조회되었습니다.")
				.data(memberItemIdsResponse)
				.build());
	}
}
