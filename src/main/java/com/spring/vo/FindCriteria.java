package com.spring.vo;

public class FindCriteria extends PageCriteria {

	private String findType;
	private String keyWord;

//	
//	public String getFindType() {
//		return findType;
//	}
//	
//	public void setFindType(String findType) {
//		this.findType = findType;
//	}
//	
//	public String getKeyWord(){
//		return keyWord;
//	}
//	
//	public void setKeyword(String keyWord) {
//		this.keyWord=keyWord;
//	}

	public String getFindType() {
		return findType;
	}

	public void setFindType(String findType) {
		this.findType = findType;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	@Override
	public String toString() {
		return super.toString() + "~~~~FindCriteria [findType=" + findType + ", keyWord=" + keyWord + "]";
	}

}
