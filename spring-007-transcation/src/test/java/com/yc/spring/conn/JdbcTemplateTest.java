package com.yc.spring.conn;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTemplateTest {
	
	//JdbcTemplate, JdbcDaoSupport
	//NamedParameterJdbcTemplate , NamedParameterJdbcDaoSupport
	
	
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp(){
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		jdbcTemplate = (JdbcTemplate) cxt.getBean("jdbcTemplate");
	}

	
	@Test
	public void testUpdate() {
		//jdbcTemplate.update()处理 insert  update delete
		int result =  jdbcTemplate.update("insert into account values(?, ?)", "yc", 1000);
		assertEquals(result, 1);
	}

}
