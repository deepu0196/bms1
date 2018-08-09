package com.bms.service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.bms.exception.BookMyShowException;
import com.bms.repository.UserRepository;


@Service
public class IRedisServiceImpl implements IRedisService {

	@Autowired
	private RedisTemplate<String, Object> template;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	private  UserRepository userRepository;

	@Override
	public Object getValue(String key) {
		return template.opsForValue().get(key);
	}

	@Override
	public void setValue(String key, String value) {
		template.opsForValue().set(key, value);
		template.expire(key, 5, TimeUnit.MINUTES);

	}

	@Override
	public String deleteValue(String key) {
		Set<String> keys = new HashSet<>();
		keys.add(key);
		template.opsForValue().getOperations().delete(keys);
		return "logged out done";

	}

	@Override
	public boolean checkToken(HttpServletRequest request) throws BookMyShowException {
		boolean value=false;
		String token=request.getHeader("Authorization");
		Object email=getValue(token);
		String s=email+"";
		if(token==null)
		{throw new BookMyShowException("Access Denied to you.. ");}
		else if(userRepository.findByUsername(s)!=null)
		value=true;
		return value;
	}
	
	

	

}
