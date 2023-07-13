package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MemberItemRequest {
	@NonNull
	private int itemId;
}
