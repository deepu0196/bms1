package com.bms.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bms.dto.UserDto;
import com.bms.exception.BookMyShowException;
import com.bms.response.ResponseData;

public interface IUserService {

	public ResponseData loginFunction(UserDto userDto);

	public String registerFunction(UserDto userDto);

	public List<UserDto> fetchAllUser() throws BookMyShowException;

	public ResponseData logoutFunction(UserDto userDto, HttpServletRequest request);

}
