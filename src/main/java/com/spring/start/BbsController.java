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

	// 요청 > controller > serviceimpl >
	// dao > serviceimpl > controller > jsp

	private static final Logger logger = LoggerFactory.getLogger(BbsController.class);

	@Inject
	private BbsService bsvc; // BbsService 객체 주입

	// get방식-입력,조회 post방식-url창에 보여지지않는 정보
	// 입력
	@RequestMapping(value = "/writer", method = RequestMethod.GET) // 13줄에서 bbs다음에 나오는 경로 입력, method지정안하면 get방식
	public void writerGET(BbsVO bvo, Model model) throws Exception { // 입력한 내용을 전달 받아야 하므로 model ,전달될 데이터가 있다면 model이라는
																		// 객체이용
		logger.info("writer GET.....");
	}

	@RequestMapping(value = "/writer", method = RequestMethod.POST)
//	public String writerPost(BbsVO bvo,Model model) throws Exception{
	public String writerPost(BbsVO bvo, RedirectAttributes reAttr) throws Exception {
		// RedirectAttibutes 객체는 리다이렉트 시점에 한번만 사용되느니 데이터를 전송할 수 있는
		// addFlashAttribute()메소드를 지원
		logger.info("writer POST.....");
		logger.info(bvo.toString());

		bsvc.writer(bvo);
//		model.addAttribute("result","success");
		reAttr.addFlashAttribute("result", "success");

//		model.addAttribute("result","success");

//		return "/bbs/resultOk";  //결과페이지 확인용도
		return "redirect:/bbs/list";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(Model model) throws Exception {
		logger.info("글목록 가져오기....");
		model.addAttribute("list", bsvc.list());

	}

	// @RequestParam : 서블릿에서 request.getParameter()과 유사한 기능이다.
	// Servlet의 request는 HttpServeltRequest
	// @RequestParam과 HttpServletRequest의 차이점: 문자열, 숫자, 날짜 등의 형변환 여부
	@RequestMapping(value="/readPage", method = RequestMethod.GET)
	public void read(@RequestParam("bid") int bid, @ModelAttribute("pCri") PageCriteria pCria,Model model) throws Exception {

		model.addAttribute(bsvc.read(bid)); // addAttribute에 key는 클래스의 이름을 자동으로 소문자로 시작해서 저장된다
		
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
	
	

	// 수정조회
//	@RequestMapping(value = "/modify", method = RequestMethod.GET)
//	public void modifyGET(int bid, Model model) throws Exception {
//		model.addAttribute(bsvc.read(bid));
//
//	}
	

	//수정조회
	@RequestMapping(value="modifyPage", method = RequestMethod.GET)
	public void modifyGet(@RequestParam("bid")  int bid, 
			@ModelAttribute("pCri") PageCriteria pCria, 
			Model model) throws Exception{
		
		model.addAttribute(bsvc.read(bid));
		
	}
	

	

	// 수정처리
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
