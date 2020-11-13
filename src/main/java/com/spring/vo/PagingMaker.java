package com.spring.vo;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PagingMaker {
	private int totalData; // ��ü ������ ����
	private int startPage; // ������ ����� ���۹�ȣ
	private int endPage; // ������ ����� ����ȣ
	private boolean prev; // ������ư�� ��Ÿ���� �ο� ��
	private boolean next; // ������ư�� ��Ÿ���� �ο� ��

	private int displayPageNum = 10;// ��������Ͽ� ��Ÿ�� ������ ��ȣ�� ��
	private PageCriteria cri;

	public void setCri(PageCriteria cri) {
		this.cri = cri;
	}

	public void setTotalData(int totalData) {
		this.totalData = totalData;
		getPagingData();
	}

	/*
	 * i=3/100 System.out.println(i) =====> 0
	 * 
	 * i = (int)3.0/100 System.out.println(i); ======>33
	 * 
	 * di=3.0/100.0; System.out.println(i); =======>0.03
	 * 
	 * di = 3/100.0 System.out.pringln(i); =======>0.03;
	 * 
	 * di=100(double)3; System.out.println(i); ========>33.33333
	 */
	private void getPagingData() {
		endPage = (int) (Math.ceil(cri.getPage() / (double) displayPageNum) * displayPageNum);

		startPage = (endPage - displayPageNum) + 1;

		int finalEndPage = (int) (Math.ceil(totalData / (double) cri.getNumPerPage()));

		if (endPage > finalEndPage) {
			endPage = finalEndPage;
		} // getPagingData()

		prev = startPage == 1 ? false : true;
		next = endPage * cri.getNumPerPage() >= totalData ? false : true;
	}
	
	
	public String makeURI(int page) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("numPerPage", cri.getNumPerPage())
				.build();
		
		return uriComponents.toUriString();
	}

	
	//setter getter
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public int getTotalData() {
		return totalData;
	}

	public PageCriteria getCri() {
		return cri;
	}

}
