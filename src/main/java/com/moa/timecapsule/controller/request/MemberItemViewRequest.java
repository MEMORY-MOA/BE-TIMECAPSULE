package com.moa.timecapsule.controller.request;

import com.moa.timecapsule.entity.ItemType;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class MemberItemViewRequest {
	@NonNull
	private ItemType itemType;
}
