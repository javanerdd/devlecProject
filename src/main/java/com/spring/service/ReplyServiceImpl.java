package com.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.ReplyDAO;
import com.spring.vo.PageCriteria;
import com.spring.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Inject
	private ReplyDAO dao;
	
	@Override
	public void inputReply(ReplyVO rvo) throws Exception{
		dao.write(rvo);
	}
	
	@Override
	public List<ReplyVO> replyList(Integer bid) throws Exception{
		return dao.relist(bid);
	}
	
	@Override
	public void modifyReply(ReplyVO rvo) throws Exception{
		dao.reModify(rvo);
	}
	
	@Override
	public void delReply(Integer rebid) throws Exception{
		dao.reDelete(rebid);
	}
	
	@Override
	public List<ReplyVO> replyListPage(Integer bid, PageCriteria pCri) throws Exception{
		
		return dao.reListPage(bid, pCri);
	}
	
	@Override
	public int reCount(Integer bid) throws Exception{
		return dao.reCount(bid);
	}
	
	
	
	
	
	

}
