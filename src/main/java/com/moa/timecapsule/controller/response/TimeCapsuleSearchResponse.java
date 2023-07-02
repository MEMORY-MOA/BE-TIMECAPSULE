package com.moa.timecapsule.controller.response;

import java.util.List;

import com.moa.timecapsule.dto.TimeCapsuleSearchDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleSearchResponse {
	private int timeCapsulesCnt;
	private int timeCapsulesPage;
	private List<TimeCapsuleSearchDto> timeCapsuleSearchDtoList;
}
