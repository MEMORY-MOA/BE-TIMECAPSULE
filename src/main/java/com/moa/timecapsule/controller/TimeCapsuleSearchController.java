package com.moa.timecapsule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleSearchResponse;
import com.moa.timecapsule.dto.FriendIdListDto;
import com.moa.timecapsule.dto.FriendSearchDto;
import com.moa.timecapsule.dto.TimeCapsuleSearchDto;
import com.moa.timecapsule.mapper.TimeCapsuleSearchMapper;
import com.moa.timecapsule.service.TimeCapsuleSearchService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/time-capsules")
@RequiredArgsConstructor
@Slf4j
public class TimeCapsuleSearchController {
	private final TimeCapsuleSearchService timeCapsuleSearchService;
	private final TimeCapsuleSearchMapper timeCapsuleSearchMapper;

	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchTimeCapsule(@RequestHeader("memberId") UUID memberId,
		@RequestParam String keyword) {
		FriendSearchDto friendSearchDto = timeCapsuleSearchMapper.toDto(memberId, keyword);
		FriendIdListDto friendIdListDto = timeCapsuleSearchService.findFriendsIdByNickname(friendSearchDto);

		List<TimeCapsuleSearchDto> timeCapsuleSearchDtoList =
			timeCapsuleSearchService.findTimeCapsuleByKeyword(memberId, friendIdListDto, keyword);
		List<TimeCapsuleSearchResponse> timeCapsuleSearchResponseList = new ArrayList<>();
		for (TimeCapsuleSearchDto timeCapsuleSearchDto : timeCapsuleSearchDtoList) {
			TimeCapsuleSearchResponse timeCapsuleSearchResponse = timeCapsuleSearchMapper.dtoToResponse(
				timeCapsuleSearchDto);
			timeCapsuleSearchResponseList.add(timeCapsuleSearchResponse);
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 검색이 완료되었습니다.")
				.data(timeCapsuleSearchResponseList)
				.build());
	}
}
