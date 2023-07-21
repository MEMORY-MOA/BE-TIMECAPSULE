package com.moa.timecapsule.controller.response;

import java.util.List;

import com.moa.timecapsule.dto.ItemDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TimeCapsuleItemListResponse {
	private List<ItemDto> itemDtoList;
}
