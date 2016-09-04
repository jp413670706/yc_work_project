package springranscation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {

	@Test
	public void test() {
		ApplicationContext cxt=new ClassPathXmlApplicationContext("spring.xml");
		Person person=(Person) cxt.getBean("person");
		System.out.println(person);
	}
	@Test
	public void test01() {
		ApplicationContext cxt=new ClassPathXmlApplicationContext("spring.xml");
		User user=(User) cxt.getBean("user");
		System.out.println(user);
	}
}
