package com.yc.spring.day01;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.spring.day01.impl.A4Paper;
import com.yc.spring.day01.impl.B5Paper;
import com.yc.spring.day01.impl.BlackWriteInk;
import com.yc.spring.day01.impl.ColorInk;

public class PrintMachineTest {

	@Test
	public void testPrint02() {
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		PrintMachine pm01 = (PrintMachine) cxt.getBean("pm");
		pm01.print();
		
	}
}
