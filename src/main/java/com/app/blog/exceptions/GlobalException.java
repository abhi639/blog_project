package com.app.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.blog.payloads.ApiResponce;
@RestControllerAdvice
public class GlobalException {

	
	
@ExceptionHandler(ResouceNotFoundException.class)
public ResponseEntity<ApiResponce>resourceNotfoundException(ResouceNotFoundException reource){
	
	String message=reource.getMessage();
	ApiResponce apiresponce=new ApiResponce(message,false);
	return new ResponseEntity<ApiResponce>(apiresponce,HttpStatus.NOT_FOUND);
	
	
}
@ExceptionHandler(MethodArgumentNotValidException.class	)
public ResponseEntity<Map<String, String>>handlemethodargumetexception(MethodArgumentNotValidException ex){
	
	
	Map<String, String>resp=new HashMap<>();
	ex.getBindingResult().getAllErrors().forEach((error)->{
		String fieldName=((FieldError)error).getField();
		String message=error.getDefaultMessage();
		resp.put(fieldName, message);
	});
	 
	return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
}
}
