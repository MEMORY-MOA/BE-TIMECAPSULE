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

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleContentRequest;
import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleContentIdResponse;
import com.moa.timecapsule.dto.TimeCapsuleFileDto;
import com.moa.timecapsule.dto.TimeCapsuleTextDto;
import com.moa.timecapsule.entity.ContentType;
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

	@GetMapping("/open")
	public ResponseEntity<ResponseDto> openTimeCapsule(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId) {
		List<TimeCapsuleTextDto> timeCapsuleTextDtoList = timeCapsuleContentService.selectTimeCapsuleTextList(
			timeCapsuleId);

		/* !!!!! file 추가되면 수정해야하는 부분 */
		// List<TimeCapsuleTextDto> timeCapsuleTextDtoList = timeCapsuleContentService.selectTimeCapsuleFileList(
		// 			timeCapsuleId);

		TimeCapsuleFileDto fileDto = TimeCapsuleFileDto.builder().timeCapsuleFileId(UUID.randomUUID()).contentType(
			ContentType.IMAGE).build();
		List<TimeCapsuleFileDto> timeCapsuleFileDtoList = new ArrayList<>();
		timeCapsuleFileDtoList.add(fileDto);
		timeCapsuleFileDtoList.add(fileDto);

		List<TimeCapsuleContentIdResponse> responseList = new ArrayList<>();

		for (TimeCapsuleTextDto timeCapsuleTextDto : timeCapsuleTextDtoList) {
			responseList.add(toTimeCapsuleContentIdResponse(timeCapsuleTextDto.getTimeCapsuleTextId()));
		}

		for (TimeCapsuleFileDto timeCapsuleFileDto : timeCapsuleFileDtoList) {
			responseList.add(toTimeCapsuleContentIdResponse(timeCapsuleFileDto.getTimeCapsuleFileId(),
				timeCapsuleFileDto.getContentType()));
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐이 열렸습니다.")
				.data(responseList)
				.build());
	}

	@PostMapping("/text")
	public ResponseEntity<ResponseDto> generateTimeCapsuleText(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId,
		@RequestBody GenerateTimeCapsuleContentRequest request) {
		timeCapsuleContentService.insertTimeCapsuleText(
			timeCapsuleContentDtoMapper.fromGenerateTimeCapsuleTextRequest(member, timeCapsuleId, request));

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("편지가 타임캡슐에 담겼습니다.")
				.build());
	}

	@GetMapping("/text/{text-id}")
	public ResponseEntity<ResponseDto> openTimeCapsuleText(@RequestHeader("member") UUID member,
		@PathVariable("time-capsule") UUID timeCapsuleId,
		@PathVariable("text-id") UUID timeCapsuleTextId) {
		TimeCapsuleTextDto timeCapsuleTextDto = timeCapsuleContentService.selectTimeCapsuleText(timeCapsuleTextId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("편지를 열었습니다")
				.data(timeCapsuleContentDtoMapper.toTimeCapsuleTextResponse(timeCapsuleTextDto))
				.build());
	}

	private TimeCapsuleContentIdResponse toTimeCapsuleContentIdResponse(UUID contentId) {
		return TimeCapsuleContentIdResponse.builder()
			.contentId(contentId)
			.contentType(ContentType.TEXT)
			.build();
	}

	private TimeCapsuleContentIdResponse toTimeCapsuleContentIdResponse(UUID contentId, ContentType contentType) {
		return TimeCapsuleContentIdResponse.builder()
			.contentId(contentId)
			.contentType(contentType)
			.build();
	}
}
