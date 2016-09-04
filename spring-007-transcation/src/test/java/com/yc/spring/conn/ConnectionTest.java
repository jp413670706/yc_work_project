package com.yc.spring.conn;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConnectionTest {
	@Test
	public void testConn(){
		ApplicationContext cxt = new ClassPathXmlApplicationContext("spring.xml");
		DataSource ds = (DataSource) cxt.getBean("dataSource");
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(con);
	}
}
