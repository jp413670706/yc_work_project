package com.yc.spring.day01;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Hello {
	private Person who;
	private List<String> lists;
	private String[] arrs;
	private Map<String, String> map;
	private Properties props;

	public Hello() {
		System.out.println("我是hello构造函数....");
	}

	public Person getWho() {
		return who;
	}

	public void setWho(Person who) {
		this.who = who;
	}

	public List<String> getLists() {
		return lists;
	}

	public void setLists(List<String> lists) {
		this.lists = lists;
	}

	public String[] getArrs() {
		return arrs;
	}

	public void setArrs(String[] arrs) {
		this.arrs = arrs;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	
	@Override
	public String toString() {
		return "Hello [who=" + who + "\nlists=" + lists + "\narrs=" + Arrays.toString(arrs) + "\nmap=" + map + "\nprops=" + props + "]";
	}

	public String sayHello(String name) {
		return String.format("%s对%s说：你好吗？", who == null ? "佰生人" : who.getName(), name);
	}
}
