package com.spring.dao;

import java.util.List;

import com.spring.vo.BbsVO;
import com.spring.vo.FindCriteria;
import com.spring.vo.PageCriteria;

public interface BbsDAO {
	
	public void insert(BbsVO bvo) throws Exception;//글쓰기
	
	public BbsVO read(Integer bid) throws Exception; //상세
	
	public void update(BbsVO bvo) throws Exception;//수정
	
	public void delete(Integer bid) throws Exception;//삭제
	
	public List<BbsVO> list() throws Exception; //목록
	
	public List<BbsVO> listPage(int page) throws Exception; //페이징
	
	public List<BbsVO> listCriteria(PageCriteria pageCria) throws Exception;
	
	public int countData(PageCriteria pageCria) throws Exception; // 데이터 총 갯수
	
	
	
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception;
	
	public int findCountData(FindCriteria findCri) throws Exception;
	
	
}
