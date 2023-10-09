package com.moa.timecapsule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleTextRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/time-capsules-content")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleContentController {

	private final TimeCapsuleContentService timeCapsuleContentService;
	private final TimeCapsuleContentDtoMapper timeCapsuleContentDtoMapper;

	@GetMapping("/{time-capsule}/open")
	@Operation(summary = "타임캡슐 열기 API_yejin")
	public ResponseEntity<ResponseDto> openTimeCapsule(@RequestHeader("member") UUID member,
													   @PathVariable("time-capsule") UUID timeCapsuleId) {
		List<TimeCapsuleTextDto> timeCapsuleTextDtoList = timeCapsuleContentService.selectTimeCapsuleTextList(
			timeCapsuleId);

		System.out.println("텍스트 검색");
		 List<TimeCapsuleFileDto> timeCapsuleFileDtoList = timeCapsuleContentService.selectTimeCapsuleFileList(
		 			timeCapsuleId);
		System.out.println("이미지검색");

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

	@PostMapping
	@Operation(summary = "타임캡슐 컨텐츠 생성(텍스트+파일) API_yejin")
	public ResponseEntity<ResponseDto> generateTimeCapsuleContents(@RequestHeader("member") UUID member,
		GenerateTimeCapsuleContentRequest request) {

		System.out.println(request.getTimecapsuleId());
		System.out.println(member);

		timeCapsuleContentService.checkTimeCapsuleMember(request.getTimecapsuleId(), member);

		if (request.getText() != null) {
			for (GenerateTimeCapsuleTextRequest textRequest: request.getText()) {
				timeCapsuleContentService.insertTimeCapsuleText(
					timeCapsuleContentDtoMapper.fromGenerateTimeCapsuleTextRequest(member, request.getTimecapsuleId(), textRequest)
				);
			}
		}

		if (request.getMultipartFiles() != null) {
			for (MultipartFile multipartFile: request.getMultipartFiles()) {
				timeCapsuleContentService.insertTimeCapsuleImage(multipartFile, request.getTimecapsuleId(), member);
			}
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("컨텐츠가 타임캡슐에 담겼습니다.")
				.build());
	}

	@PostMapping("/{time-capsule}/text")
	@Operation(summary = "타임캡슐 텍스트 생성 API_yejin")
	public ResponseEntity<ResponseDto> generateTimeCapsuleText(@RequestHeader("member") UUID member,
															   @PathVariable("time-capsule") UUID timeCapsuleId,
															   @RequestBody GenerateTimeCapsuleTextRequest request) {
		timeCapsuleContentService.insertTimeCapsuleText(
			timeCapsuleContentDtoMapper.fromGenerateTimeCapsuleTextRequest(member, timeCapsuleId, request));

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("편지가 타임캡슐에 담겼습니다.")
				.build());
	}

	@GetMapping("/{time-capsule}/text/{text-id}")
	@Operation(summary = "타임캡슐 텍스트 열기 API_yejin")
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
