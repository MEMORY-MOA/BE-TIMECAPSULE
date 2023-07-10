package com.moa.timecapsule.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeCapsuleSearchDto {

	private String title;

	private LocalDate closedAt;

	private LocalDate openedAt;

	private boolean isOpened;
}
