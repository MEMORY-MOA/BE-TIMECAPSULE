package com.moa.timecapsule.controller.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.moa.timecapsule.dto.TimeCapsuleMemberDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleResponse {

	private UUID timeCapsuleId;

	private String title;

	private LocalDate closedAt;

	private LocalDate openedAt;

	private boolean isOpened;

	private UUID creator;

	private List<TimeCapsuleMemberDto> friends;

	private LocalDateTime createdAt;

	private int shape;
}
