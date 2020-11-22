package com.spring.start;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.ReplyDAO;
import com.spring.vo.ReplyVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*xml"})
public class replyDAOTest {
	
	@Inject
	private ReplyDAO rdao;
	
	private static Logger logger= LoggerFactory.getLogger(replyDAOTest.class);
	
//	@Test
//	public void writeTest() throws Exception{
//		ReplyVO rvo = new ReplyVO();
//		rvo.setBid(4);
//		rvo.setReplyContent("±è1");
//		rvo.setReplyer("±è1");
//		
//		rdao.write(rvo);
//		
//	}
	
	@Test
	public void updateTest() throws Exception{
		ReplyVO rvo= new ReplyVO();
		rvo.setRebid(1);
		rvo.setReplyContent("ÀÚ¹Ù¼öÁ¤ÇÔ");
		
		rdao.reModify(rvo);
	}
	
	
	

}
