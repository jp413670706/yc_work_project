package com.yc.spring.day01.impl;

import org.springframework.stereotype.Component;

import com.yc.spring.day01.Paper;

@Component("a4")
public class A4Paper implements Paper {
	@Override
	public String getSize() {
		return "A4";
	}

}
