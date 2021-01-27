package com.example.crud.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.crud.exception.ResourceNotFoundException;
import com.example.crud.model.dto.ExceptionResponse;

@RestController
@ControllerAdvice
public class CrudExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handlerBadRequestException(Exception ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(
				new ExceptionResponse(LocalDate.now(), ex.getMessage(), request.getDescription(false)),
				HttpStatus.BAD_REQUEST);
	}
}