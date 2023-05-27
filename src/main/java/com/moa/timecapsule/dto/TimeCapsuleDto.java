package com.moa.timecapsule.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class TimeCapsuleDto extends BaseDto {

	private UUID timeCapsuleId;

	private String title;

	private LocalDate closedAt;

	private LocalDate openedAt;

	private boolean isOpened;

	private UUID creator;

	private List<TimeCapsuleMemberDto> friends;

	public void insertFriends(List<TimeCapsuleMemberDto> friends) {
		this.friends = friends;
	}
}
