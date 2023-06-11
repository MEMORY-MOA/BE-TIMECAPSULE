package com.moa.timecapsule.exception;

public class NotFoundException extends RuntimeException {
	private int code;

	public NotFoundException(String message) {
		super(message);
		this.code = 500;
	}

	public int getCode() {
		return code;
	}
}
