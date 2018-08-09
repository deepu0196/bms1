package com.bms.controller;


import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bms.dto.UserDto;
import com.bms.exception.BookMyShowException;
import com.bms.model.User;
import com.bms.response.ResponseData;
import com.bms.service.IUserService;

@RestController
@RequestMapping("/bms")
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(User.class);
	ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private IUserService userService;
	
	//SIGNIN
	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	public ResponseData SignIn(@RequestBody  UserDto userDto) throws BookMyShowException {
		ResponseData user = userService.loginFunction(userDto);
		if (user == null)
			throw new BookMyShowException("Cannot log in.. Not Authorised.."); 
		else
			return user;
	}

	//SIGNUP	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String SignUp(@RequestBody UserDto userDto) {
		String messgae = userService.registerFunction(userDto);
		if (messgae == null)
			return "Cannot Register this seller..";
		else
			return "Registered Successfully";
	}
	
	//LOGOUT
	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
	public ResponseData logout(@RequestBody UserDto userDto,HttpServletRequest request) {
		ResponseData response = userService.logoutFunction(userDto,request);
		return response;
	}


	
	

}
