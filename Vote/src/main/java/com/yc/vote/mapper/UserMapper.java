package com.yc.vote.mapper;

import com.yc.vote.entity.User;

public interface UserMapper {

	int insertUser(User user);

	User findUserByNP(User user);

}
