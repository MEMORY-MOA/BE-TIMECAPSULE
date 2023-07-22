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

import com.moa.timecapsule.controller.request.MemberItemViewRequest;
import com.moa.timecapsule.controller.response.MemberItemListResponse;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.dto.MemberItemDto;
import com.moa.timecapsule.dto.MemberItemListDto;
import com.moa.timecapsule.dto.MemberItemViewDto;
import com.moa.timecapsule.mapper.MemberItemMapper;
import com.moa.timecapsule.service.MemberItemService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules/user-item")
@RequiredArgsConstructor
@Slf4j
public class MemberItemController {
	private final MemberItemMapper memberItemMapper;
	private final MemberItemService memberItemService;

	@PostMapping("/{itemId}")
	@Operation(summary = "사용자가 보유하고 있는 아이템 등록 API_Ahin.K")
	public ResponseEntity<ResponseDto> registerMemberItem(@RequestHeader("memberId") UUID memberId,
		@PathVariable("itemId") int itemId) {
		MemberItemDto memberItemDto = memberItemMapper.toDto(memberId, itemId);
		memberItemService.insertMemberItem(memberItemDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("사용자 타임캡슐 아이템이 등록되었습니다.")
				.build());
	}

	@PostMapping
	@Operation(summary = "사용자가 보유하고 있는 아이템 조회 API_Ahin.K")
	public ResponseEntity<ResponseDto> viewTimeCapsuleItem(@RequestHeader("memberId") UUID memberId,
		@RequestBody MemberItemViewRequest request) {
		MemberItemViewDto memberItemViewDto = memberItemMapper.toDto(memberId, request);
		MemberItemListDto memberItemListDto = memberItemService.findMemberItem(memberItemViewDto);
		MemberItemListResponse memberItemListResponse = memberItemMapper.dtoToResponse(memberItemListDto);
		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("사용자 아이템이 조회되었습니다.")
				.data(memberItemListResponse)
				.build());
	}
}
