package com.yc.spring.aop.impl;
import com.yc.spring.aop.ArithmeticCalcultion;
public class ArithmeticCalcultionImpl implements ArithmeticCalcultion {
	@Override
	public int add(int num1, int num2) {
		int result = num1 + num2;
		return result;
	}
	@Override
	public int mul(int num1, int num2) {
		int result = num1 - num2;
		return result;
	}
}