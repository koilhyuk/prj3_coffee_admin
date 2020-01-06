package kr.co.sist.team2.vo;

public class SearchQnaVO {
	
	private String keyword, field;
	private int currentPage, startNum, endNum;
	
	public SearchQnaVO() {
	}
	
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}


	@Override
	public String toString() {
		return "SearchQnaVO [keyword=" + keyword + ", field=" + field + ", currentPage=" + currentPage + ", startNum="
				+ startNum + ", endNum=" + endNum + "]";
	}
	
}
