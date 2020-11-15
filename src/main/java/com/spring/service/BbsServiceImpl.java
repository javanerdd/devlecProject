package com.spring.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.spring.dao.BbsDAO;
import com.spring.vo.BbsVO;
import com.spring.vo.FindCriteria;
import com.spring.vo.PageCriteria;


@Service
public class BbsServiceImpl implements BbsService{
	
	@Inject
	private BbsDAO bdao;
	
	@Override
	public void writer(BbsVO bvo) throws Exception{
		bdao.insert(bvo);
	}
	
	@Override
	public BbsVO read(Integer bid) throws Exception{
		return bdao.read(bid);
	}
	
	@Override
	public void modify(BbsVO bvo) throws Exception{
		bdao.update(bvo);
	}
	
	@Override
	public void remove(Integer bid) throws Exception{
		bdao.delete(bid);
	}

	@Override
	public List<BbsVO> list() throws Exception{
		return bdao.list();
	}
	
	@Override
	public List<BbsVO> listCriteria(PageCriteria pCria) throws Exception{
		return bdao.listCriteria(pCria);
	}
	
	@Override
	public int listCountData(PageCriteria pCria) throws Exception{
		return bdao.countData(pCria);
	}
	
	@Override
	public List<BbsVO> listFind(FindCriteria findCri) throws Exception{
		return bdao.listFind(findCri);
	}
	
	@Override
	public int findCountData(FindCriteria findCri) throws Exception{
		return bdao.findCountData(findCri);
	}
	
	// ��û > controller > serviceimpl >
	// dao > serviceimpl > controller > jsp

}
