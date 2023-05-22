package com.moa.timecapsule.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class BaseDto {
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
