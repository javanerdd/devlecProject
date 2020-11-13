package com.spring.start;

import javax.inject.Inject;

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
import com.spring.vo.PageCriteria;
import com.spring.vo.PagingMaker;

@Controller
@RequestMapping("/bbs/*")
public class BbsController {

	// ��û > controller > serviceimpl >
	// dao > serviceimpl > controller > jsp

	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);

	@Inject
	private BbsService bsvc; // BbsService ��ü ����

	// get���-�Է�,��ȸ post���-urlâ�� ���������ʴ� ����
	// �Է�
	@RequestMapping(value = "/writer", method = RequestMethod.GET) // 13�ٿ��� bbs������ ������ ��� �Է�, method�������ϸ� get���
	public void writerGET(BbsVO bvo, Model model) throws Exception { // �Է��� ������ ���� �޾ƾ� �ϹǷ� model ,���޵� �����Ͱ� �ִٸ� model�̶��
																		// ��ü�̿�
		logger.info("writer GET.....");
	}

	@RequestMapping(value = "/writer", method = RequestMethod.POST)
//	public String writerPost(BbsVO bvo,Model model) throws Exception{
	public String writerPost(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
		// RedirectAttibutes ��ü�� �����̷�Ʈ ������ �ѹ��� ���Ǵ��� �����͸� ������ �� �ִ�
		// addFlashAttribute()�޼ҵ带 ����
		logger.info("writer POST.....");
		logger.info(bvo.toString());

		bsvc.writer(bvo);
//		model.addAttribute("result","success");
		reAttr.addFlashAttribute("result", "success");

//		model.addAttribute("result","success");

//		return "/bbs/resultOk";  //��������� Ȯ�ο뵵
		return "redirect:/bbs/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("�۸�� ��������....");
		model.addAttribute("list", bsvc.list());

	}

	// @RequestParam : �������� request.getParameter()�� ������ ����̴�.
	// Servlet�� request�� HttpServeltRequest
	// @RequestParam�� HttpServletRequest�� ������: ���ڿ�, ����, ��¥ ���� ����ȯ ����
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCria,Model model) throws Exception {

		model.addAttribute(bsvc.read(bid)); // addAttribute�� key�� Ŭ������ �̸��� �ڵ����� �ҹ��ڷ� �����ؼ� ����ȴ�
		
	}

//	@RequestMapping(value="/delete", method = RequestMethod.POST)
//	public String delete(@RequestParam("bid") int bid, RedirectAttributes reAttr) throws Exception {
//
//		bsvc.remove(bid);
//		reAttr.addFlashAttribute("result", "success");
//
//		return "redirect:/bbs/list";
//	}
	
	@RequestMapping(value="/delPage", method=RequestMethod.GET)
	public String delPage(@RequestParam("bid") int bid, PageCriteria pCria, RedirectAttributes reAttr) throws Exception{
		bsvc.remove(bid);
		reAttr.addAttribute("page",pCria.getPage());
		reAttr.addAttribute("numPerPage",pCria.getNumPerPage());
		reAttr.addFlashAttribute("result","success");
		
		return "redirect:/bbs/pageList";
	}
	
	

	// ������ȸ
//	@RequestMapping(value = "/modify", method = RequestMethod.GET)
//	public void modifyGET(int bid, Model model) throws Exception {
//		model.addAttribute(bsvc.read(bid));
//
//	}
	

	//������ȸ
	@RequestMapping(value="modifyPage", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bid")  int bid, 
			@ModelAttribute("pCri") PageCriteria pCria, 
			Model model) throws Exception{
		
		model.addAttribute(bsvc.read(bid));
		
	}
	

	

	// ����ó��
//	@RequestMapping(value = "/modify", method = RequestMethod.POST)
//	public String modifyPOST(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
//		logger.info("modifyPOST().................");
//		bsvc.modify(bvo);
//		reAttr.addFlashAttribute("result", "success");
//
//		return "redirect:/bbs/list";
//	}
	
	
	
	
	

	@RequestMapping(value = "/pageListTest", method = RequestMethod.GET)
	public void pageListTest(PageCriteria pCria, Model model) throws Exception {
		logger.info("pageList()................");

		model.addAttribute("list", bsvc.listCriteria(pCria));
	}

	@RequestMapping(value = "/pageList", method = RequestMethod.GET)
	public void pageList(PageCriteria pCria, Model model) throws Exception {
		logger.info(pCria.toString());
		model.addAttribute("list",bsvc.listCriteria(pCria));
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCria);
//		pagingMaker.setTotalData(155);
		
		pagingMaker.setTotalData(bsvc.listCountData(pCria));
		model.addAttribute("pagingMaker",pagingMaker);
	}
}
