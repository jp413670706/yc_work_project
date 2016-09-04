package com.yc.spring.aop.impl;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 做日志处理的切面
 * 
 * @author JP
 *
 */
public class LoggingAspect {

	/**
	 * 前置通知
	 * 
	 * @param joinPoint连接点对象
	 */
	public void beforeMethod(JoinPoint joinPoint) {
		// 方法签名( 返回类型， 方法名, 参数列表)
		String methodName = joinPoint.getSignature().getName();// 执行的方法名
		Object[] args = joinPoint.getArgs(); // 取到参数列表
		System.out.println(String.format("yc ==> method %s begin with%s", methodName, Arrays.toString(args)));
	}

	/**
	 * 后置通知, 不带返回值, 但当执行方法出现异常时， 照样调用
	 * 
	 * @param joinPoint连接点对象
	 */
	public void afterMethod(JoinPoint joinPoint) {
		// 方法签名( 返回类型， 方法名, 参数列表)
		String methodName = joinPoint.getSignature().getName();// 执行的方法名
		System.out.println(String.format("yc ==> method %s end....", methodName));
	}

	/**
	 * 后置带值通知, 带返回值, 但当执行方法出现异常时， 不会调用
	 * 
	 * @param joinPoint连接点对象
	 */
	public void afterReturingMethod(JoinPoint joinPoint, int result) {
		// 方法签名( 返回类型， 方法名, 参数列表)
		String methodName = joinPoint.getSignature().getName();// 执行的方法名
		System.out.println(String.format("yc ==> method %s end result with %d", methodName, result));
	}

	/**
	 * 后置异常通知, 带返回值, 但当执行方法出现异常时， 不会调用
	 * 
	 * @param joinPoint连接点对象
	 */
	public void afterThrowingMethod(JoinPoint joinPoint, Exception e) {
		// 方法签名( 返回类型， 方法名, 参数列表)
		String methodName = joinPoint.getSignature().getName();// 执行的方法名
		System.out.println(String.format("yc ==> method %s occur exception with %s", methodName, e.getMessage()));
	}

	/**
	 * 环绕通知
	 * @param pjp 进行操作连接点对象
	 * @return 具体方法执行操作的结果
	 */
	public Object aroundMethod(ProceedingJoinPoint pjp) {
		String methodName = pjp.getSignature().getName();// 执行的方法名
		Object[] args = pjp.getArgs();
		Object result = null;
		try {
			System.out.println(String.format("yc ==> method %s begin with%s", methodName, Arrays.toString(args)));
			result = pjp.proceed();
			System.out.println(String.format("yc ==> method %s end result with %d", methodName, result));
		} catch (Throwable e) {
			System.out.println(String.format("yc ==> method %s occur exception with %s", methodName, e.getMessage()));
		}finally{
			System.out.println(String.format("yc ==> method %s end....", methodName));
		}
		
		return result;
	}
}
