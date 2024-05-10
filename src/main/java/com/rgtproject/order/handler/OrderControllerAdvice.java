package com.rgtproject.order.handler;

import java.sql.SQLException;
import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rgtproject.global.auth.AuthInterceptor;
import com.rgtproject.global.common.ErrorResponse;
import com.rgtproject.global.exception.CustomException;
import com.rgtproject.member.controller.MemberController;
import com.rgtproject.order.controller.OrderController;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackageClasses = {OrderController.class, AuthInterceptor.class})
public class OrderControllerAdvice {
	@ExceptionHandler(IllegalStateException.class)
	public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException ex) {
		log.error("IllegalStateException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"OrderIllegalStateException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
		log.error("IllegalArgumentException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"OrderIllegalArgumentException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorResponse> handleSQLException(SQLException ex) {
		log.error("SQLException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"OrderSQLException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException ex) {
		log.error("MalformedJwtException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"MalformedJwtException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(ExpiredJwtException.class)
	public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException ex) {
		log.error("Exception", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"ExpiredJwt");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UnsupportedJwtException.class)
	public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException ex) {
		log.error("Exception", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"UnsupportedJwt");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
		log.error("RuntimeException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"RuntimeException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex) {
		log.error("AuthenticationException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), List.of(ex.getMessage()),"AuthenticationException");
		return new ResponseEntity<>(errorResponse,HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleException(Exception ex) {
		log.error("OrderException", ex);
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), List.of(ex.getMessage()),"OrderException");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(CustomException.InvalidTokenException.class)
	public ResponseEntity<ErrorResponse> handleInvalidTokenException(CustomException.InvalidTokenException ex) {
		log.error("InvalidTokenException", ex);
		ErrorResponse errorResponse = new ErrorResponse(ex.getCode(), List.of(ex.getMessage()),"InvalidToken");
		return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}



}
