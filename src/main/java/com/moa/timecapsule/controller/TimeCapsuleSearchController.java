package com.moa.timecapsule.controller;

import java.util.UUID;

import com.moa.timecapsule.controller.request.SessionTokenRequest;
import com.moa.timecapsule.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.moa.timecapsule.controller.response.ResponseDto;
import com.moa.timecapsule.controller.response.TimeCapsuleSearchResponse;
import com.moa.timecapsule.mapper.TimeCapsuleSearchMapper;
import com.moa.timecapsule.service.TimeCapsuleSearchService;

import io.swagger.v3.oas.annotations.Operation;
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
	@Operation(summary = "타임캡슐 이름 or 타임캡슐 맴버 닉네임 검색 API_Ahin.K",
		description = "page 0부터 시작, page 별 10개씩 조회(마지막 페이지는 10개 이하), 타임캡슐 오픈 날짜 순으로 정렬, swagger사용시 pageable에는 josn 괄호 {} 만 보내야 에러 안남")
	public ResponseEntity<ResponseDto> searchTimeCapsule(@RequestHeader("member") UUID memberId,
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

	@GetMapping("/{time-capsule}/link")
	@Operation(summary = "타임캡슐 접속 연결_yejin")
	public ResponseEntity<ResponseDto> linkTimeCapsule(@RequestHeader("member") UUID member,
														  @PathVariable("time-capsule") UUID timeCapsuleId,
														  HttpServletRequest request) {

		TokenDto tokenDto = timeCapsuleSearchService.link(member, timeCapsuleId);

		HttpSession session = request.getSession();
		session.setAttribute("linkTokenId", tokenDto.getToken());
		session.setMaxInactiveInterval(5*60);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 접속 한 명 증가하였습니다.")
				.data(timeCapsuleSearchMapper.dtoToTokenResponse(tokenDto))
				.build());
	}

	@PostMapping("/{time-capsule}/unlink")
	@Operation(summary = "타임캡슐 접속 연결 끊기_yejin")
	public ResponseEntity<ResponseDto> unlinkTimeCapsule(@RequestBody SessionTokenRequest sessionTokenRequest,
														 @PathVariable("time-capsule") UUID timeCapsuleId,
														 HttpServletRequest request) {
		System.out.println("unlink");
		timeCapsuleSearchService.unlink(sessionTokenRequest.getToken(), timeCapsuleId);

		HttpSession session = request.getSession();
		UUID user = (UUID)session.getAttribute("linkTokenId");

		if (user != null && user.equals(sessionTokenRequest.getToken())) {
			session.removeAttribute("linkTokenId");
			session.invalidate();
		}

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 접속 한 명 감소하였습니다.")
				.build());
	}

	@GetMapping("/{time-capsule}/link/count")
	@Operation(summary = "타임캡슐 접속자 수_yejin")
	public ResponseEntity<ResponseDto> getTimeCapsuleLinkCount(@PathVariable("time-capsule") UUID timeCapsuleId) {

		TimeCapsuleLinkCountDto timeCapsuleLinkCountDto = timeCapsuleSearchService.linkCount(timeCapsuleId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.httpStatus(HttpStatus.OK)
				.msg("타임캡슐 접속자 수를 조회하였습니다.")
				.data(timeCapsuleSearchMapper.dtoToTimeCapsuleLinkCountResponse(timeCapsuleLinkCountDto))
				.build());
	}
}
