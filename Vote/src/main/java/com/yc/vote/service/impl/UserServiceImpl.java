package com.yc.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.vote.entity.User;
import com.yc.vote.mapper.UserMapper;
import com.yc.vote.service.UserService;
import com.yc.vote.util.Encrypt;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean register(User user) {
		
		//密码加密操作
		user.setVuPassword(Encrypt.md5AndSha(user.getVuPassword()));
		try {
			return userMapper.insertUser(user) > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User login(User user) {
		//密码加密操作
		user.setVuPassword(Encrypt.md5AndSha(user.getVuPassword()));
		return userMapper.findUserByNP(user);
	}
}