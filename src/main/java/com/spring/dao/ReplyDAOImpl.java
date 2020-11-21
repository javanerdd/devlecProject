package com.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<ReplyVO> relist(Integer bid) throws Exception{
		return sqlSession.selectList("relist",bid);
	}
	
	@Override
	public void write(ReplyVO rvo) throws Exception{
		sqlSession.insert("write",rvo);
	}
	
	@Override
	public void modify(ReplyVO rvo) throws Exception{
		sqlSession.update("update", rvo);
	}
	
	@Override
	public void delete(Integer rebid) throws Exception{
		sqlSession.delete("delete",rebid);
	}
	
	
}
