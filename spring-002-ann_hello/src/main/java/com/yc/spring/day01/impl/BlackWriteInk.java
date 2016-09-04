package com.yc.spring.day01.impl;

import org.springframework.stereotype.Component;

import com.yc.spring.day01.Ink;

@Component("bwi")
public class BlackWriteInk implements Ink {

	@Override
	public String getColor() {
		return "黑白色";
	}

}
