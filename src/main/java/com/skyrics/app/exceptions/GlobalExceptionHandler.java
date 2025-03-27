package com.skyrics.app.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.skyrics.app.payloads.ApiRespons;

import jakarta.persistence.RollbackException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiRespons> responseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message = ex.getMessage();
		ApiRespons apiRespons = new ApiRespons(message, false);
		return new ResponseEntity<ApiRespons>(apiRespons,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(TransactionSystemException.class)
	protected ResponseEntity<List<String>> handleTransactionException(TransactionSystemException ex) throws Throwable {
		Throwable cause = ex.getCause();
		if (!(cause instanceof RollbackException)) throw cause;
		if (!(cause.getCause() instanceof ConstraintViolationException)) throw cause.getCause();
		ConstraintViolationException validationException = (ConstraintViolationException) cause.getCause();
		List<String> messages = validationException.getConstraintViolations().stream()
				.map(ConstraintViolation::getMessage).collect(Collectors.toList());
		
		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}
	
	
	

	//	@ExceptionHandler(MethodArgumentNotValidException.class)
	//	public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
	//		HashMap<String, String> resp = new HashMap<>();
	//		ex.getBindingResult().getAllErrors().forEach((error)->{
	//			String fieldName=((FieldError) error).getField();
	//			String message=error.getDefaultMessage();
	//			resp.put(fieldName, message);
	//		});
	//		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	//	}
}
