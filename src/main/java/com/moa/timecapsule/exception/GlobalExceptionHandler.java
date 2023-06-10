package com.moa.timecapsule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.moa.timecapsule.controller.response.ResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ResponseDto<?>> handleValidationException(MethodArgumentNotValidException ex) {
		ResponseDto<?> responseDto = ResponseDto.builder()
			.httpStatus(HttpStatus.BAD_REQUEST)
			.msg("유효하지 않은 요청: " + ex.getMessage())
			.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ResponseDto<?>> handleNotFoundException(NotFoundException ex) {
		ResponseDto<?> responseDto = ResponseDto.builder()
			.httpStatus(HttpStatus.NOT_FOUND)
			.msg("찾을 수 없음: " + ex.getMessage())
			.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
}
