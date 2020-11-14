package com.spring.start;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.service.BbsService;
import com.spring.vo.FindCriteria;
import com.spring.vo.PagingMaker;

@Controller
@RequestMapping("/fbbs/*")
public class FindController {
	private static final Logger logger = LoggerFactory.getLogger(FindController.class);
	
	@Inject
	private BbsService bsvc;
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public void list(@ModelAttribute("fCri") FindCriteria fCri, Model model) throws Exception{
		
		logger.info(fCri.toString());
		
		model.addAttribute("list",bsvc.listCriteria(fCri));
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(fCri);
		
		pagingMaker.setTotalData(bsvc.listCountData(fCri));
		model.addAttribute("pagingMaker",pagingMaker);
	}
}
