package com.moa.timecapsule.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TimeCapsuleDto {

	private UUID timeCapsuleId;

	private String title;

	private LocalDate closedAt;

	private LocalDate openedAt;

	private boolean isOpened;

	private UUID member;

	private List<UUID> friends;
}
