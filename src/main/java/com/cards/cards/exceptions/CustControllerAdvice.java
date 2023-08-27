package com.cards.cards.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.cards.cards.dtos.ErrorResponse;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class CustControllerAdvice extends MessageSourceAdviceCtrl {
	protected CustControllerAdvice(MessageSource messageSource) {
		super(messageSource);
	}

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		Map<String, String> errors = new HashMap<String, String>();
		e.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), errors.toString()));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleExceptionDataIntegrityViolationException(
			DataIntegrityViolationException e) {
		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		log.error("Invalid input " + message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
	}

	@ExceptionHandler(MismatchedInputException.class)
	public ResponseEntity<ErrorResponse> handleExceptionMismatchedInputException(MismatchedInputException e) {
		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		log.error("Mismatched Input Exception..." + message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
	}

	@ExceptionHandler(value = { ResponseStatusException.class })
	public ResponseEntity<Object> handleApiRequestException(ResponseStatusException e) {
		String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
		log.error("ResponseStatusException Exception..." + message);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), message));
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleExceptionCustomException(CustomException e) {
		log.error("Custom Exception... " + e.getMessage());
		e.printStackTrace();
		log.error(e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), e.getMessage()));
	}

}
