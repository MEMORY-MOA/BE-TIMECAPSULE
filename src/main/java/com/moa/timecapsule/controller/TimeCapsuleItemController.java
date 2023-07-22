package com.moa.timecapsule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.request.TimeCapsuleItemRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleItemListResponse;
import com.moa.timecapsule.dto.TimeCapsuleItemIdTypeDto;
import com.moa.timecapsule.dto.TimeCapsuleItemListDto;
import com.moa.timecapsule.dto.TimeCapsuleItemRegisterDto;
import com.moa.timecapsule.dto.TimeCapsuleItemViewDto;
import com.moa.timecapsule.entity.ItemType;
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

	@PostMapping
	@Operation(summary = "타임캡슐 아이템 등록 API_Ahin.K")
	public ResponseEntity<ResponseDto> registerTimeCapsuleItem(@RequestHeader("memberId") UUID memberId,
		@PathVariable("timeCapsuleId") UUID timeCapsuleId, @RequestBody TimeCapsuleItemRequest request) {
		TimeCapsuleItemRegisterDto timeCapsuleItemRegisterDto = timeCapsuleItemMapper.toDto(memberId, timeCapsuleId,
			toTimeCapsuleItemIdTypeDtoList(request));
		timeCapsuleItemService.insertTimeCapsuleItem(timeCapsuleItemRegisterDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 아이템이 등록되었습니다.")
				.build());
	}

	@GetMapping
	@Operation(summary = "타임캡슐 아이템 조회 API_Ahin.K")
	public ResponseEntity<ResponseDto> viewTimeCapsuleItem(@RequestHeader("memberId") UUID memberId,
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

	private List<TimeCapsuleItemIdTypeDto> toTimeCapsuleItemIdTypeDtoList(TimeCapsuleItemRequest request) {
		List<TimeCapsuleItemIdTypeDto> timeCapsuleItemIdTypeDtoList = new ArrayList<>();
		timeCapsuleItemIdTypeDtoList.add(timeCapsuleItemMapper.toDto(request.getBoxShapeItemId(), ItemType.boxShape));
		timeCapsuleItemIdTypeDtoList.add(timeCapsuleItemMapper.toDto(request.getBoxColorItemId(), ItemType.boxColor));
		timeCapsuleItemIdTypeDtoList.add(
			timeCapsuleItemMapper.toDto(request.getBoxPatternItemId(), ItemType.boxPattern));
		timeCapsuleItemIdTypeDtoList.add(timeCapsuleItemMapper.toDto(request.getLockShapeItemId(), ItemType.lockShape));
		return timeCapsuleItemIdTypeDtoList;
	}
}
