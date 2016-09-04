package com.yc.spring.day01;

public class PrintMachine {
	private Ink ink;
	private Paper paper;

	public Ink getInk() {
		return ink;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setInk(Ink ink) {
		this.ink = ink;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public void print() {
		System.out.println(String.format("正在%s纸上进行%s打印内容...", paper.getSize(), ink.getColor()));
	}
}
