package com.yc.spring.aop.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理
 * @author JP
 */
public class MyDynamicProxy<T> {
	private T obj;

	@SuppressWarnings("unchecked")
	public T getProxyNewInstance(T obj) {
		this.obj = obj;
		// 动态代理对象，由那个类加载器加载
		ClassLoader loader = obj.getClass().getClassLoader();

		// 要代理，处理的方法
		Class<?>[] interfaces = obj.getClass().getInterfaces();

		// 在代理方法执行前后，要进行的处理
		InvocationHandler h = new InvocationHandler() {

			/**
			 * proxy ：代理对象 method : 具体要执行的方法 args :执行方法的参数
			 */
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				//处理前处理
				System.out.println(String.format("yc ==> method %s begin with%s", method.getName(), Arrays.toString(args)));
				//方法执行
				int result = (int) method.invoke(MyDynamicProxy.this.obj, args);
				//处理后处理
				System.out.println(String.format("yc ==> method %s end with %d", method.getName(), result));
				return result;
			}
		};
		return (T) Proxy.newProxyInstance(loader, interfaces, h);
	}
}
