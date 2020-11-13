package com.spring.dao;

import com.spring.vo.MemberVO;

public interface MemberDAO {
	
	public String getTime(); //현재시간체크
	
	public void insertMember(MemberVO mvo); //회원입력

}
