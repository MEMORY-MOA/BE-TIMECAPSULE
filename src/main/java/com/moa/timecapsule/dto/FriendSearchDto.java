package com.moa.timecapsule.dto;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FriendSearchDto {
	UUID memberId;
	String keyword;
}
