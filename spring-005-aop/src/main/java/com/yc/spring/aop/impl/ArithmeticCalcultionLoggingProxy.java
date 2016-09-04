package com.yc.spring.aop.impl;

import com.yc.spring.aop.ArithmeticCalcultion;

/**
 * 静态代理  : 只能处理一个种代理
 * @author JP
 *
 */
public class ArithmeticCalcultionLoggingProxy implements ArithmeticCalcultion {
	
	private ArithmeticCalcultion ac;
	
	public ArithmeticCalcultionLoggingProxy(ArithmeticCalcultion ac) {
		this.ac = ac;
	}
	@Override
	public int add(int num1, int num2) {
		System.out.println(String.format("yc ==> method add begin with[%d, %d]", num1, num2));
		int result = ac.add(num1, num2);
		System.out.println(String.format("yc ==> method add end with %d", result));
		return result;
	}

	@Override
	public int mul(int num1, int num2) {
		System.out.println(String.format("yc ==> method mul begin with[%d, %d]", num1, num2));
		int  result = ac.mul(num1, num2);
		System.out.println(String.format("yc ==> method mul end with %d", result));
		return result;
	}

}
