package com.bms.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bms.dto.UserDto;
import com.bms.exception.BookMyShowException;
import com.bms.model.User;
import com.bms.repository.UserRepository;
import com.bms.response.ResponseData;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Transactional
public class UserServiceImpl implements IUserService{
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	ModelMapper modelMapper = new ModelMapper();
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private IRedisService iredis;
	

	@Override
	public ResponseData loginFunction(UserDto userDto) {
		ResponseData response = new ResponseData();
		User user=userRepository.findByUsername(userDto.getUsername());
		if(userRepository.findByUsername(userDto.getUsername())==null)
		{response.setCode(HttpStatus.UNAUTHORIZED.value());
		response.setMessage("Not a valid Email..");
		response.setResponse("Access Denied");
		return response;
		}
		if(bCryptPasswordEncoder.matches(userDto.getPassword(), user.getPassword())) {
		String token=null;
		try {
			System.out.println("In here");
			 token = Jwts.builder().setSubject("bms"+user.getUsername()+"bms").claim("scope", "self groups/admins")
						.signWith(SignatureAlgorithm.HS256, "secret".getBytes("UTF-8"))
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis() + 10000)).compact();
			System.out.println(" " + token + " ");
			iredis.setValue(token,user.getUsername());
			response.setCode(HttpStatus.OK.value());
			response.setMessage("user successfully logged In");
			response.setResponse("Access Given");
			response.setToken(token);
			return response;
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		}
		else
		{response.setCode(HttpStatus.UNAUTHORIZED.value());
		response.setMessage("Email/Password Not valid");
		response.setResponse("Access Denied");
		return response;
		}
		return response;
	 
	}

	@Override
	public String registerFunction(UserDto userDto) {
		User user = new User();
		user = modelMapper.map(userDto, User.class);
			user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
			 userRepository.save(user);
			return "Registered Successfully";
		} 
	 
	 

	@Override
	public List<UserDto> fetchAllUser() throws BookMyShowException {
		List<User> userList = userRepository.findAll();
		List<UserDto>  userDtoList = new ArrayList<>();
		UserDto userDto = new UserDto();
		for (User user : userList) {
			userDto = modelMapper.map(user, UserDto.class);
			userDtoList.add(userDto);
		}
		if (userDtoList.isEmpty()) {
			throw new  BookMyShowException("No users Registered..");
		}
		return userDtoList;
		
		 
	}

	@Override
	public ResponseData logoutFunction(UserDto userDto, HttpServletRequest request) {
		String token=request.getHeader("Authorization");
		iredis.deleteValue(token);
		ResponseData responseDto=new ResponseData();
		responseDto.setCode(HttpStatus.OK.value());
		responseDto.setMessage("Successfully logged out");
		responseDto.setToken("Token Deleted");
		responseDto.setResponse("To get Access, Login Again");
		return responseDto;
		
	}

}
