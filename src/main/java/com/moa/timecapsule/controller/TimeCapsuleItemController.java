package com.moa.timecapsule.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.request.TimeCapsuleItemRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleItemListResponse;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.mapper.TimeCapsuleItemMapper;
import com.moa.timecapsule.service.TimeCapsuleItemService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules/{timeCapsuleId}/item")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleItemController {
	private final TimeCapsuleItemMapper timeCapsuleItemMapper;
	private final TimeCapsuleItemService timeCapsuleItemService;

	@PatchMapping
	@Operation(summary = "타임캡슐 아이템 등록 API_Ahin.K", description = "수정하고 싶은 필드에만 값을 넣고 나머지는 null로 보내서 수정 가능")
	public ResponseEntity<ResponseDto> registerTimeCapsuleItem(@RequestHeader("member") UUID memberId,
		@PathVariable("timeCapsuleId") UUID timeCapsuleId, @RequestBody TimeCapsuleItemRequest request) {
		TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto = timeCapsuleItemMapper.toDto(memberId, timeCapsuleId,
			request);
		timeCapsuleItemService.insertTimeCapsuleItem(timeCapsuleItemRegisterDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 아이템이 등록되었습니다.")
				.build());
	}

	@GetMapping
	@Operation(summary = "타임캡슐 아이템 조회 API_Ahin.K")
	public ResponseEntity<ResponseDto> viewTimeCapsuleItem(@RequestHeader("member") UUID memberId,
		@PathVariable("timeCapsuleId") UUID timeCapsuleId) {
		TimeCapsuleItemViewDto timeCapsuleItemViewDto = timeCapsuleItemMapper.toDto(memberId, timeCapsuleId);
		TimeCapsuleItemListDto timeCapsuleItemListDto = timeCapsuleItemService.findTimeCapsuleItem(
			timeCapsuleItemViewDto);
		TimeCapsuleItemListResponse timeCapsuleItemListResponse = timeCapsuleItemMapper.toResponse(
			timeCapsuleItemListDto);
		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 아이템이 조회되었습니다.")
				.data(timeCapsuleItemListResponse)
				.build());
	}
}
