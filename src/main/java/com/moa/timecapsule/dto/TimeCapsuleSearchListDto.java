package com.moa.timecapsule.dto;

import java.util.List;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TimeCapsuleSearchListDto extends BaseDto {
	private int timeCapsulesCnt;
	private int timeCapsulesPage;
	private List<TimeCapsuleSearchDto> timeCapsuleSearchDtoList;
}
