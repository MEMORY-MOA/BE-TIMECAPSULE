package com.moa.timecapsule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.controller.request.GenerateTimeCapsuleTextRequest;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

		List<TimeCapsuleFileDto> timeCapsuleFileDtoList = timeCapsuleContentService.selectTimeCapsuleFileList(
			timeCapsuleId);

		List<TimeCapsuleContentIdResponse> responseList = new ArrayList<>();

		for (TimeCapsuleTextDto timeCapsuleTextDto : timeCapsuleTextDtoList) {
			responseList.add(toTimeCapsuleContentIdResponse(timeCapsuleTextDto.getTimeCapsuleTextId(), timeCapsuleTextDto.getColor()));
		}

		for (TimeCapsuleFileDto timeCapsuleFileDto : timeCapsuleFileDtoList) {
			responseList.add(toTimeCapsuleContentIdResponse(timeCapsuleFileDto.getTimeCapsuleFileId(),
				timeCapsuleFileDto.getContentType(), timeCapsuleFileDto.getFileUrl()));
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐이 열렸습니다.")
				.data(responseList)
				.build());
	}

	@PostMapping(path = "/{time-capsule}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	@Operation(summary = "타임캡슐 컨텐츠 생성(텍스트+파일) API_yejin")
	public ResponseEntity<ResponseDto> generateTimeCapsuleContents(@RequestHeader("member") UUID member, @PathVariable("time-capsule") UUID timeCapsuleId,
																   @RequestPart(value = "request", required = false) GenerateTimeCapsuleContentRequest request,
																   @RequestPart(value = "multipartFiles", required = false) List<MultipartFile> multipartFiles) {

		timeCapsuleContentService.checkTimeCapsuleMember(timeCapsuleId, member);

		if (request.getTextList() != null) {
			for (GenerateTimeCapsuleTextRequest textRequest : request.getTextList()) {
				timeCapsuleContentService.insertTimeCapsuleText(
					timeCapsuleContentDtoMapper.fromGenerateTimeCapsuleTextRequest(member, timeCapsuleId, textRequest)
				);
			}
		}

		if (multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				timeCapsuleContentService.insertTimeCapsuleImage(multipartFile, timeCapsuleId, member);
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

	@GetMapping("/{time-capsule}/file/{file-id}")
	@Operation(summary = "타임캡슐 파일 열기 API_yejin")
	public ResponseEntity<ResponseDto> openTimeCapsuleFile(@RequestHeader("member") UUID member,
														   @PathVariable("time-capsule") UUID timeCapsuleId,
														   @PathVariable("file-id") UUID timeCapsuleFileId) {
		TimeCapsuleFileDto timeCapsuleFileDto = timeCapsuleContentService.selectTimeCapsuleFile(timeCapsuleFileId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("편지를 열었습니다")
				.data(timeCapsuleContentDtoMapper.toTimeCapsuleFileResponse(timeCapsuleFileDto))
				.build());
	}

	private TimeCapsuleContentIdResponse toTimeCapsuleContentIdResponse(UUID contentId, String color) {
		return TimeCapsuleContentIdResponse.builder()
			.contentId(contentId)
			.contentType(ContentType.TEXT)
			.color(color)
			.build();
	}

	private TimeCapsuleContentIdResponse toTimeCapsuleContentIdResponse(UUID contentId, ContentType contentType, String fileUrl) {
		return TimeCapsuleContentIdResponse.builder()
			.contentId(contentId)
			.contentType(contentType)
			.fileUrl(fileUrl)
			.build();
	}
}
