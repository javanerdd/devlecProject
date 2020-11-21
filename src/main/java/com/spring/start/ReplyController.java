package com.spring.start;

import java.util.List;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
