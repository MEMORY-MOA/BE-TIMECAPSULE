package com.moa.timecapsule.exception;

public class FileUploadException extends RuntimeException {
	private int code;

	public FileUploadException(String message) {
		super(message);
		this.code = 500;
	}

	public int getCode() {
		return code;
	}
}
