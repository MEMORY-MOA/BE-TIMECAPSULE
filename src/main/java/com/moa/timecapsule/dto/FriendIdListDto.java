package com.moa.timecapsule.dto;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendIdListDto {
	private List<UUID> friendIdList;
}
