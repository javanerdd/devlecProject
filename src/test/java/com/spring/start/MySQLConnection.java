package com.spring.start;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;


public class MySQLConnection {
	
	private static final String DRIVER =
			"com.mysql.jdbc.Driver";
	
	private static final String URL=
			"jdbc:mysql://127.0.0.1:3306/devlec_spring_prj";
	
	private static final String USER ="springUser";
	private static final String PWD="1234";
	
	@Test
	public void testConn() throws Exception{
		Class.forName(DRIVER);
		
		try(Connection conn =DriverManager.getConnection(URL,USER,PWD)){
			System.out.println(conn);
			System.out.println(conn);
			System.out.println("디비연결 성공");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
