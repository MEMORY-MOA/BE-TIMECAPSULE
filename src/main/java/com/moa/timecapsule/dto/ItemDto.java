package com.moa.timecapsule.dto;

import com.moa.timecapsule.entity.ItemType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {
	private int itemId;
	private ItemType itemType;
	private String item;
}
