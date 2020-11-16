package com.spring.start;

import javax.inject.Inject;
import javax.naming.RefAddr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.service.BbsService;
import com.spring.vo.BbsVO;
import com.spring.vo.FindCriteria;
import com.spring.vo.PagingMaker;

@Controller
@RequestMapping("/fbbs/*")
public class FindController {
	private static final Logger logger = LoggerFactory.getLogger(FindController.class);

	@Inject
	private BbsService bsvc;

	@RequestMapping(value="/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception {

		logger.info(fCri.toString());

//		model.addAttribute("list",bsvc.listCriteria(fCri));
		model.addAttribute("list", bsvc.listFind(fCri));

		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(fCri);

//		pagingMaker.setTotalData(bsvc.listCountData(fCri));
		pagingMaker.setTotalData(bsvc.findCountData(fCri));

		model.addAttribute("pagingMaker", pagingMaker);
	}

	@RequestMapping(value="/readPage", method=RequestMethod.GET)
	public void readPage(@RequestParam("bid") int bid, 
						@ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception{
		
		model.addAttribute(bsvc.read(bid));
	}

	
	@RequestMapping(value="delPage", method=RequestMethod.POST)
	public String delPage(@RequestParam("bid") int bid, 
				FindCriteria fCri, RedirectAttributes reAttr) throws Exception {
		
		bsvc.remove(bid);
		
		//���� ����������� ���ƿ;� �Ǳ⶧���� ����¡������ �����ϱ� ���� �Ѱ��ش�
		reAttr.addAttribute("page", fCri.getPage());
		reAttr.addAttribute("numPerPage", fCri.getNumPerPage());
		reAttr.addAttribute("keyWord", fCri.getKeyWord());
		reAttr.addAttribute("findType", fCri.getFindType());
		
		reAttr.addFlashAttribute("result", "success");
		
		return "redirect:/fbbs/list";
	}
	
	//������ȸ ������
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public void modifyGET(int bid, @ModelAttribute("fCri") FindCriteria fCri, Model model ) throws Exception {
		
		model.addAttribute(bsvc.read(bid));
	}
	
	//����ó�� ������
	@RequestMapping(value="/modifyPage", method=RequestMethod.POST)
	public String modifyPOST(BbsVO bvo, FindCriteria fCri, RedirectAttributes reAttr) throws Exception{
		
		logger.info(fCri.toString());
		bsvc.modify(bvo);
		
		reAttr.addAttribute("page",fCri.getPage());
		reAttr.addAttribute("numPerPage", fCri.getNumPerPage());
		reAttr.addAttribute("keyWord",fCri.getKeyWord());
		reAttr.addAttribute("findType", fCri.getFindType());
		
		reAttr.addFlashAttribute("result","success");
		
		logger.info(reAttr.toString());
		return "redirect:/fbbs/list";
		
	}
	
	//�۾��� ������ ��û
	@RequestMapping(value="/writer", method=RequestMethod.GET)
	public void writerGET() throws Exception{
		logger.info("writerGET() ȣ��...........");
	}
	
	//DB �� �Է� ó��
	@RequestMapping(value="/writer", method=RequestMethod.POST)
	public String writerPOST(BbsVO bvo, FindCriteria fCri, RedirectAttributes reAttr) throws Exception {
		
		logger.info("writerPOST() ȣ��...........");
		bsvc.writer(bvo);
		
		
		reAttr.addFlashAttribute("result","success");
		
		return "redirect:/fbbs/list";
	}
	
	
	
	
	
}













// ��û > controller > serviceimpl >
// dao > serviceimpl > controller > jsp