package com.yc.spel;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

public class HelloTest {

	@Test
	public void testHello() {
		Hello hello=new Hello();
		String result=hello.sayHello();
		System.out.println(result);
	}
	
	  @Test  
	public void helloWorld() {  
		  	//1）创建解析器：SpEL使用ExpressionParser接口表示解析器，提供SpelExpressionParser默认实现；
	        ExpressionParser parser = new SpelExpressionParser();  
	        //2）解析表达式：使用ExpressionParser的parseExpression来解析相应的表达式为Expression对象。
	        Expression expression = parser.parseExpression("('Hello' + ' World').concat(#start)");  
	        // 3）构造上下文：准备比如变量定义等等表达式需要的上下文数据。
	        EvaluationContext context = new StandardEvaluationContext();  
	        context.setVariable("start","");  
	        //4）求值：通过Expression接口的getValue方法根据上下文获得表达式值。
	        Assert.assertEquals("Hello World", expression.getValue(context));  
	        String str=(String) expression.getValue(context);
	        System.out.println(str);
	}  

}
