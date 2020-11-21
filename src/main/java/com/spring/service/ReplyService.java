package com.spring.service;

import java.util.List;

import com.spring.vo.ReplyVO;

public interface ReplyService {
	
	public void inputReply(ReplyVO rvo) throws Exception;
	
	public List<ReplyVO> replyList(Integer bid) throws Exception;
	
	public void modifyReply(ReplyVO rvo) throws Exception;
	
	public void delReply(Integer rebid) throws Exception;


}
