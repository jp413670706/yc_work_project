package com.yc.spring.day01.impl;

import org.springframework.stereotype.Component;

import com.yc.spring.day01.Paper;

@Component("b5")
public class B5Paper implements Paper {
	@Override
	public String getSize() {
		return "B5";
	}

}
