package com.bms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BookMyShowException  extends Exception {

	Logger logger = LoggerFactory.getLogger(BookMyShowException.class);

	public BookMyShowException(String message) {
		super(message);
		logger.info(message);
	}

}
