package com.yc.spring.day01;

import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean<Person> {
	private Person person;
	
	 public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public Person getObject() throws Exception {
		if (person.getGender().intern() == "女"){
			return null;
		}
		return person;
	}

	@Override
	public Class<?> getObjectType() {
		return Person.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
