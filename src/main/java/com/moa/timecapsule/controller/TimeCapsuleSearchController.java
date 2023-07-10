package com.moa.timecapsule.controller;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import com.moa.timecapsule.dto.TimeCapsuleSearchListDto;
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
		@RequestParam String keyword, @RequestParam int page,
		@PageableDefault(size = 10, sort = "openedAt", direction = Sort.Direction.ASC) Pageable pageable) {
		FriendSearchDto friendSearchDto = timeCapsuleSearchMapper.toDto(memberId, keyword);
		FriendIdListDto friendIdListDto = timeCapsuleSearchService.findFriendsIdByNickname(friendSearchDto);

		TimeCapsuleSearchListDto timeCapsuleSearchListDto =
			timeCapsuleSearchService.findTimeCapsuleByKeyword(memberId, friendIdListDto, keyword, pageable, page);
		TimeCapsuleSearchResponse timeCapsuleSearchResponse =
			timeCapsuleSearchMapper.dtoToResponse(timeCapsuleSearchListDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 검색이 완료되었습니다.")
				.data(timeCapsuleSearchResponse)
				.build());
	}
}
