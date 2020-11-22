package com.spring.start;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.fabric.Response;
import com.spring.service.ReplyService;
import com.spring.vo.PageCriteria;
import com.spring.vo.PagingMaker;
import com.spring.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Inject
	private ReplyService replyService;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<String> input(@RequestBody ReplyVO rvo) {
		ResponseEntity<String> resEntity = null;

		try { // sql문으로 처리 하므로 try catch
			replyService.inputReply(rvo);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}

	//댓글 리스트
	@RequestMapping(value = "/selectAll/{bid}", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bid") Integer bid) {

		ResponseEntity<List<ReplyVO>> resEntity = null;

		try {
			resEntity = new ResponseEntity<>(replyService.replyList(bid), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return resEntity;

	}

	// 댓글 수정
	@RequestMapping(value="/{rebid}", method={RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rebid") Integer rebid, @RequestBody ReplyVO rvo){
		ResponseEntity<String> resEntity = null;
		
		try {
		rvo.setRebid(rebid);	
		replyService.modifyReply(rvo);
		
		resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	//댓글 삭제
	@RequestMapping(value="/{rebid}", method=RequestMethod.DELETE)
	public ResponseEntity<String> reDel(@PathVariable("rebid") Integer rebid){
		
		ResponseEntity<String> resEntity = null;
		
		try {
			replyService.delReply(rebid);
			resEntity = new ResponseEntity<String>("success", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	
	//댓글 페이징처리
	@RequestMapping(value="/{bid}/{page}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> reListPage(
			@PathVariable("bid") Integer bid,
			@PathVariable("page") Integer page){
		
		ResponseEntity<Map<String,Object>> resEntity = null;
		
		try {
		PageCriteria pCri = new PageCriteria();
		pCri.setPage(page);
		
		PagingMaker pagingMaker = new PagingMaker();
		pagingMaker.setCri(pCri);
		
		Map<String, Object> reMap = new HashMap<String,Object>();
		List<ReplyVO> reList = replyService.replyListPage(bid, pCri);
	
		reMap.put("reList", reList);
		
		int reCount = replyService.reCount(bid);
		pagingMaker.setTotalData(reCount);
		
		reMap.put("pagingMaker",pagingMaker);
		resEntity = new ResponseEntity<Map<String,Object>>(reMap,HttpStatus.OK);
		
		}catch(Exception e) {
			e.printStackTrace();
			resEntity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return resEntity;
	}
	


	
	
	
	
	
	
	
	

}
