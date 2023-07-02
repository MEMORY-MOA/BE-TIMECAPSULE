package com.moa.timecapsule.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class TimeCapsuleSearchDto extends BaseDto {

	private UUID timeCapsuleId;

	private String title;

	private LocalDate closedAt;

	private LocalDate openedAt;

	private boolean isOpened;
}
