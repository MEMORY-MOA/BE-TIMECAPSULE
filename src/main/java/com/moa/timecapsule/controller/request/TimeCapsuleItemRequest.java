package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TimeCapsuleItemRequest {
	private Integer boxShapeItemId;
	private Integer effectItemId;
	private Integer lockShapeItemId;
}
