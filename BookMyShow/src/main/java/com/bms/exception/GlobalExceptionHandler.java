package com.bms.exception;


import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public Exception blogError(Exception exception) {
		logger.error("Error Ocurred :~ " + exception.getMessage());
		return exception;
	}

	@ExceptionHandler(BookMyShowException.class)
	public Exception blogError(BookMyShowException exception) {
		logger.info("Error Ocurred :: " + exception.getMessage());
		return exception;
	}
	
	@ExceptionHandler(SQLException.class)
	public Exception sqlerror(Exception exception) {
		logger.error("Error Ocurred ~~ " + exception.getMessage());
		return exception;
	}
}
