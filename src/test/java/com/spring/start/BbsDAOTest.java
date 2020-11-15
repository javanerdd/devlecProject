package com.spring.start;


import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.spring.dao.BbsDAO;
import com.spring.vo.BbsVO;
import com.spring.vo.FindCriteria;
import com.spring.vo.PageCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class BbsDAOTest {
	
	@Inject
	private BbsDAO bdao;

	private static Logger logger = LoggerFactory.getLogger(BbsDAOTest.class);
	
//	@Test
//	public void insertTest() throws Exception {
//		BbsVO bvo = new BbsVO();
//		bvo.setSubject("�׽�Ʈ �����Դϴ�");
//		bvo.setContent("�׽�Ʈ �����Դϴ�");
//		bvo.setWriter("ȫ�浿");
//		bdao.insert(bvo);
//	}
	
	
//	@Test
//	public void readTest() throws Exception{
//		logger.info(bdao.read(1).toString());
//		logger.info(bdao.read(2).toString());
//	}
	
	
//	@Test
//	public void updateTest() throws Exception{
//		BbsVO bvo = new BbsVO();
//		bvo.setBid(2);
//		bvo.setSubject("�����׽�Ʈ ��");
//		bvo.setContent("�����׽�Ʈ ����");
//	    bdao.update(bvo);
//	}

	
//	@Test
//	public void deleteTest() throws Exception{
//		bdao.delete(2);
//	}
	
	
//	@Test
//	public void listTest() throws Exception{
//		logger.info(bdao.list().toString());
//	}
	
	
//	@Test
//	public void listPageTest() throws Exception{
//		int page = 5;
//		
//		List<BbsVO> list = bdao.listPage(page);
//		for(BbsVO bbsVO : list) {
//			logger.info(bbsVO.getBid()+":"+bbsVO.getSubject());
//		}
//	}

	
	
//	@Test
//	public void listCriteriaTest() throws Exception{
//		
//		PageCriteria pCria = new PageCriteria();
//		pCria.setPage(3);
//		pCria.setNumPerPage(15);
//		
//		List<BbsVO> list = bdao.listCriteria(pCria);
//		
//		for(BbsVO bvo : list) {
//			logger.info(bvo.getBid()+":"+bvo.getSubject());
//		}
//		
//	}
	
	
	
	//UriComponentBuilder�� �̿��ϴ� ��: org.springframework.web.util ��Ű���� ����
//	@Test
//	public void uriTest1() throws Exception{
//		UriComponents uriComponents = 
//				UriComponentsBuilder.newInstance()
//				.path("/bbs/read")
//				.queryParam("numPerPage",20)
//				.build();
//		
//		logger.info("/bbs/read?bid=100&numPerPage=20");
//		logger.info(uriComponents.toString());
//	}
	

//	@Test
//	public void uriTest2() throws Exception{
//		UriComponents uriComponents =
//				UriComponentsBuilder.newInstance().path("/{module}/{page}")
//				.queryParam("bid", 100)
//				.queryParam("numPerPage",20)
//				.build()
//				.expand("bbs","read")
//				.encode();
//		
//		logger.info("/bbs/read?bid=100&numPerPage=20");
//				
//	}

	@Test
	public void findTest() throws Exception{
		FindCriteria cri = new FindCriteria();
		
		cri.setPage(1);
		cri.setPage(2);
		cri.setFindType("S");
		cri.setKeyword("�׽�Ʈ");
		logger.info("****************�׽�Ʈ�ڵ� ���*******************");
		List<BbsVO> list = bdao.listFind(cri);
		
		for(BbsVO bvo:list) {
			logger.info(bvo.getBid()+": " + bvo.getSubject());
		}
		
		logger.info("****************�׽�Ʈ Data���� ���*******************");
		logger.info("countData: " + bdao.findCountData(cri));
	}
	
	
	
	
	
	
}
