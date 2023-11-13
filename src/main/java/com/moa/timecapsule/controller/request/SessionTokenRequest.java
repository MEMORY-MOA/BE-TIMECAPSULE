package com.moa.timecapsule.controller.request;

import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Data
@Getter
public class SessionTokenRequest {
	private UUID token;
}
