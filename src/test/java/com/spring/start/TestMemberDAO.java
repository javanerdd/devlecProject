package com.spring.start;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.MemberDAO;
import com.spring.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class TestMemberDAO {
	
	
	@Inject
	private MemberDAO memberDAO;
	
	@Test
	public void testTime() throws Exception{
		System.out.println(memberDAO.getTime());
	}
	
	@Test
	public void testInsertMember() throws Exception{
		MemberVO mvo = new MemberVO();
		mvo.setUid("testI12312dddda23s");
		mvo.setPwd("12343");
		mvo.setUsername("È«±æsÀÌ322");
		mvo.setEmail("t3est@tes32t.c223om");
		
		memberDAO.insertMember(mvo);
	}

}
