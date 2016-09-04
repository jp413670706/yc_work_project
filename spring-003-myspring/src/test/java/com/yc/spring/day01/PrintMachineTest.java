package com.yc.spring.day01;

import org.junit.Test;

import com.yc.myspring.core.ApplicationContext;
import com.yc.spring.day01.impl.A4Paper;
import com.yc.spring.day01.impl.B5Paper;
import com.yc.spring.day01.impl.BlackWriteInk;
import com.yc.spring.day01.impl.ColorInk;

public class PrintMachineTest {

	@Test
	public void testPrint() {
		PrintMachine pm01 = new PrintMachine();
		pm01.setInk(new ColorInk());
		pm01.setPaper(new A4Paper());
		pm01.print();
		
		PrintMachine pm02 = new PrintMachine();
		pm02.setInk(new BlackWriteInk());
		pm02.setPaper(new A4Paper());
		pm02.print();
		
		PrintMachine pm03 = new PrintMachine();
		pm03.setInk(new BlackWriteInk());
		pm03.setPaper(new B5Paper());
		pm03.print();
		
		PrintMachine pm04 = new PrintMachine();
		pm04.setInk(new ColorInk());
		pm04.setPaper(new B5Paper());
		pm04.print();
	}
	
	@Test
	public void testPrint02() {
		ApplicationContext cxt = new ApplicationContext();
		PrintMachine pm01 = (PrintMachine) cxt.getBean("pm01");
		pm01.print();
		
		PrintMachine pm02 = (PrintMachine) cxt.getBean("pm02");
		pm02.print();
		
		PrintMachine pm03 = (PrintMachine) cxt.getBean("pm03");
		pm03.print();
		
		PrintMachine pm04 = (PrintMachine) cxt.getBean("pm04");
		pm04.print();
	}
}
