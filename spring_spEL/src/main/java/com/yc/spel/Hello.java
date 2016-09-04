package com.yc.spel;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class Hello {
	
	private static ExpressionParser parser = new SpelExpressionParser();
	private static Expression exp = parser.parseExpression("'Hello World'");
	private String message = (String) exp.getValue();
	
	public String sayHello(){
		return message;
	}
}
