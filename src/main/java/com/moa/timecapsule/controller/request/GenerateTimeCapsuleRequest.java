package com.moa.timecapsule.controller.request;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class GenerateTimeCapsuleRequest {
	@NonNull
	private String title;

	@NonNull
	@DateTimeFormat
	private LocalDate closedAt;

	@NonNull
	@DateTimeFormat
	private LocalDate openedAt;

	private List<UUID> friends;
}
