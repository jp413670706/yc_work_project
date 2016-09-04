package com.yc.vote.service;

import com.yc.vote.entity.User;

public interface UserService {

	boolean register(User user);

	User login(User user);
}