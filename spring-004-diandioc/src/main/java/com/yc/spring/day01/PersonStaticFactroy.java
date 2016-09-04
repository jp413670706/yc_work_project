package com.yc.spring.day01;

import java.util.HashMap;
import java.util.Map;
/**
 * 静态工厂
 * @author JP
 *
 */
public class PersonStaticFactroy {
	private static Map<String, Person> persons = new HashMap<String, Person>();
	
	static{
		persons.put("xiaoming", new Person("xiaoming", 12, "男"));
		persons.put("zhangsan", new Person("zhangsan", 35, "男"));
		persons.put("xiaohong", new Person("xiaohong", 20, "女"));
	}
	
	public static Person newInstance(String name){
		return persons.get(name);
	}
}
