package com.moa.timecapsule.controller;

import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleListDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleIdResponse;
import com.moa.timecapsule.dto.TimeCapsuleDto;
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
	@Operation(summary = "타임캡슐 생성_yejin")
	public ResponseEntity<ResponseDto> generateTimeCapsule(@RequestHeader("member") UUID member,
														   @RequestBody GenerateTimeCapsuleRequest request) {
		TimeCapsuleDto timeCapsuleDto = timeCapsuleService.insertTimeCapsule(
			timeCapsuleDtoMapper.fromGenerateTimeCapsuleRequest(member, request));

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐이 생성되었습니다.")
				.data(TimeCapsuleIdResponse.builder()
					.timeCapsuleId(timeCapsuleDto.getTimeCapsuleId())
					.build())
				.build());
	}

	@GetMapping("/{time-capsule}")
	@Operation(summary = "타임캡슐 조회 API_yejin")
	public ResponseEntity<?> getOneTimeCapsule(@RequestHeader("member") UUID member,
											   @PathVariable("time-capsule") UUID timeCapsuleId) {
		TimeCapsuleDto timeCapsuleDto = timeCapsuleService.selectTimeCapsule(
			timeCapsuleDtoMapper.toTimeCapsuleIdsDto(member, timeCapsuleId)
		);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg(timeCapsuleDto.getTitle() + " 타임캡슐 정보 조회가 완료되었습니다.")
				.data(timeCapsuleDtoMapper.toTimeCapsuleResponse(timeCapsuleDto))
				.build());
	}

	@GetMapping("/list")
	@Operation(summary = "타임캡슐 리스트 API_yejin",
		description = "page 0부터 시작, page 별 10개씩 조회(마지막 페이지는 10개 이하), 타임캡슐 오픈 날짜 순으로 정렬, swagger사용시 pageable에는 josn 괄호 {} 만 보내야 에러 안남, 주소에 list?page=0&size=3&sort=id,desc&sort=username,desc 등으로 사용 가능하나, page만 있어도 됨")
	public ResponseEntity<ResponseDto> getTimeCapsules(@RequestHeader("member") UUID member,
													   @PageableDefault(size = 1000, sort = "createdAt", direction = Sort.Direction.ASC) Pageable page) {

		TimeCapsuleListDto timeCapsuleDtoList = timeCapsuleService.selectTimeCapsules(member, page);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 정보 조회가 완료되었습니다.")
				.data(timeCapsuleDtoMapper.toTimeCapsuleListResponse(timeCapsuleDtoList))
				.build());
	}
}
