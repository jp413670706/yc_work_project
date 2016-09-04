package webtag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yc.bean.Exam;
import com.yc.biz.ExamBiz;

import junit.framework.TestCase;

//XP:   
//TDD: 测试驱动 
//自动构建,自动测试:  maven 
public class TestFavoriteBiz extends TestCase {
	ApplicationContext ac;

	@Test
	public void testAddFavorite() {
		ac = new ClassPathXmlApplicationContext("beans.xml");
		ExamBiz eb=(ExamBiz) ac.getBean("examBiz");
		
		List<Exam> list=eb.searchAllId();
		System.out.println( list );
	}
	
	@Test
	public void testAddFavorite2() {
		ac = new ClassPathXmlApplicationContext("beans.xml");
		ExamBiz eb=(ExamBiz) ac.getBean("examBiz");
		
		List<Integer> list=new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		
		List<Exam> r=eb.searchQuestions(list);
		System.out.println( r );
	}

	

}
