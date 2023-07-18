package com.moa.timecapsule.dto;

import java.util.UUID;

import com.moa.timecapsule.entity.ItemType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberItemViewDto {
	private UUID memberId;
	private ItemType itemType;
}
