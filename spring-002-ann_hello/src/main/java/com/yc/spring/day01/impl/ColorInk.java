package com.yc.spring.day01.impl;

import org.springframework.stereotype.Component;

import com.yc.spring.day01.Ink;

@Component("ci")
public class ColorInk implements Ink {

	@Override
	public String getColor() {
		return "彩色";
	}

}
