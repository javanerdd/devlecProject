package com.spring.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.BbsVO;
import com.spring.vo.FindCriteria;
import com.spring.vo.PageCriteria;

@Repository
public class BbsDAOImpl implements BbsDAO{
	
	// ��û > controller > serviceimpl >
	// dao > serviceimpl > controller > jsp

	@Inject
	public SqlSession sqlSession;
	
	@Override	
	public void insert(BbsVO bvo) throws Exception{ //�۾���
		sqlSession.insert("insert", bvo);
		
	}
	
	@Override
	public BbsVO read(Integer bid) throws Exception{ 
		return sqlSession.selectOne("read", bid);   //�ϳ��� �۸� ������
	}//��
	
	@Override
	public void update(BbsVO bvo) throws Exception{
		 sqlSession.update("update", bvo);
	}//����
	
	@Override
	public void delete(Integer bid) throws Exception{
		sqlSession.delete("delete",bid);
	}//���� 
	
	@Override
	public List<BbsVO> list() throws Exception{
		return sqlSession.selectList("list");
	}//���

	@Override
	public List<BbsVO> listPage(int page) throws Exception{
		if(page<=0) {
			page=1;
		}
		
		page =(page-1)*10;
	   //1������ (1-1)*10=0        limit 0, 10  .... 0��������10��
	   //2������ (2-1)*10=10  	   limit 10,10  .... 10��������10�� 
 		return sqlSession.selectList("listPage",page);
	}
	
	@Override
	public List<BbsVO> listCriteria(PageCriteria cria) throws Exception{
		return sqlSession.selectList("listCriteria", cria);
		
	}
	
	@Override
	public int countData(PageCriteria pCria) throws Exception{ // ������ �� ����
		return sqlSession.selectOne("countData",pCria);
	}
	
	
	@Override
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception{
		
		return sqlSession.selectList("listFind",findCri);
	}
	
	@Override
	public int findCountData(FindCriteria findCri) throws Exception{
		
		return sqlSession.selectOne("findCountData", findCri);
	}
	
	
	
	
	
	
	
}


//�۾��� �� ���� ���� ���