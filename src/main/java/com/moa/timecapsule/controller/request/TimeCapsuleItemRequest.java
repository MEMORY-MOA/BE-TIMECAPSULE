package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TimeCapsuleItemRequest {
	@NonNull
	private int decoratedItemId;
	@NonNull
	private int size;
	@NonNull
	private int x;
	@NonNull
	private int y;
}
