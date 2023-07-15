package com.moa.timecapsule.dto;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberItemListDto {
	private List<ItemDto> itemDtoList;
}
