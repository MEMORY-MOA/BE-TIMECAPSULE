package com.moa.timecapsule.controller.response;

import com.moa.timecapsule.dto.TimeCapsuleDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TimeCapsuleListResponse {
	private long timeCapsulesCnt;
	private long timeCapsulesPage;
	private List<TimeCapsuleDto> timeCapsuleList;
}
