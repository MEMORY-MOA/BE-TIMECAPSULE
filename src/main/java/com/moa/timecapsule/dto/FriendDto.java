package com.moa.timecapsule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class FriendDto {

	private UUID memberId;
	private UUID friendId;

}
