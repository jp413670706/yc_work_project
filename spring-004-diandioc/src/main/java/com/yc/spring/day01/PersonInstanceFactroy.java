package com.yc.spring.day01;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂
 * @author JP
 *
 */
public class PersonInstanceFactroy {
	private Map<String, Person> persons = new HashMap<String, Person>();

	public PersonInstanceFactroy() {
		persons.put("xiaoming", new Person("xiaoming", 12, "男"));
		persons.put("zhangsan", new Person("zhangsan", 35, "男"));
		persons.put("xiaohong", new Person("xiaohong", 20, "女"));
	}

	public Person newInstance(String name) {
		return persons.get(name);
	}
}
