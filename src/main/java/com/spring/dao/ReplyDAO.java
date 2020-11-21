package com.spring.dao;

import java.util.List;

import com.spring.vo.ReplyVO;

public interface ReplyDAO {
	
	public List<ReplyVO> relist(Integer bid) throws Exception; // ��� ����
	
	public void write(ReplyVO rvo) throws Exception;
	
	public void modify(ReplyVO rvo) throws Exception;
	
	public void delete(Integer rebid) throws Exception;
}
