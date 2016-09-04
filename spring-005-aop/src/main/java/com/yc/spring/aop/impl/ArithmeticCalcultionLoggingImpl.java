package com.yc.spring.aop.impl;

import com.yc.spring.aop.ArithmeticCalcultion;

public class ArithmeticCalcultionLoggingImpl implements ArithmeticCalcultion {

	@Override
	public int add(int num1, int num2) {
		System.out.println(String.format("yc ==> method add begin with[%d, %d]", num1, num2));
		int  result = num1 + num2;
		System.out.println(String.format("yc ==> method add end with %d", result));
		return result;
	}

	@Override
	public int mul(int num1, int num2) {
		System.out.println(String.format("yc ==> method mul begin with[%d, %d]", num1, num2));
		int  result = num1 - num2;
		System.out.println(String.format("yc ==> method mul end with %d", result));
		return result;
	}
	
	//1.代码混乱 :除了处理核心关注点外， 还要兼顾其它的处理关注点
	
	//2.代码分散: 在多个模板中，都有相同的代码。 当需求变更时， 维护成本增加
}
