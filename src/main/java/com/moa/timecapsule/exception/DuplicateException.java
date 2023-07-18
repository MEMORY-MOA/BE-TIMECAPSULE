package com.moa.timecapsule.exception;

public class DuplicateException extends RuntimeException {
	private int code;

	public DuplicateException(String message) {
		super(message);
		this.code = 409;
	}

	public int getCode() {
		return code;
	}
}
