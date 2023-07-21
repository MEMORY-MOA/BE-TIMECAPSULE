package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class TimeCapsuleItemRequest {
	@NonNull
	private int boxShapeItemId;
	@NonNull
	private int boxColorItemId;
	@NonNull
	private int boxPatternItemId;
	@NonNull
	private int lockShapeItemId;
}
