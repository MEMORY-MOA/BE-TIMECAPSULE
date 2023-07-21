package com.moa.timecapsule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeCapsuleListDto {
	private long timeCapsulesCnt;
	private long timeCapsulesPage;
	private List<TimeCapsuleDto> timeCapsuleList;
}
