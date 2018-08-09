package com.bms.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bms.dto.UserDto;
import com.bms.exception.BookMyShowException;
import com.bms.model.User;
import com.bms.repository.UserRepository;
import com.bms.service.IRedisService;
import com.bms.service.IUserService;


@RestController
@RequestMapping("/bms")
public class UserController {
	
	Logger logger = LoggerFactory.getLogger(User.class);
	ModelMapper modelMapper = new ModelMapper();
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private IRedisService iredis;
	
	//GET ALL USERS LIST
	@RequestMapping(value = "/allUsers", method = RequestMethod.GET)
	public List<UserDto> retrieveAllUsers(HttpServletRequest request) throws BookMyShowException {
		if(iredis.checkToken(request)==true)
		return userService.fetchAllUser();
		else
		return null;
	}
	

}
