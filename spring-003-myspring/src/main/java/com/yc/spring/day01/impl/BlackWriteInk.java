package com.yc.spring.day01.impl;

import com.yc.spring.day01.Ink;

public class BlackWriteInk implements Ink {

	@Override
	public String getColor() {
		return "黑白色";
	}

}
