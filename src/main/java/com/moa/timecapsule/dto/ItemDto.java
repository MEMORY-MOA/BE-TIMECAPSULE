package com.moa.timecapsule.dto;

import com.moa.timecapsule.entity.ItemType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemDto {
	private Integer itemId;
	private ItemType itemType;
	private String item;
	private String imgUrl;
}
