package kr.co.sist.team2.vo;

public class MenuSearchVO {
	private String cateSelect, menuSearch;
	private int currentPage, StartNum, endNum;
	public String getCateSelect() {
		return cateSelect;
	}
	public void setCateSelect(String cateSelect) {
		this.cateSelect = cateSelect;
	}
	public String getMenuSearch() {
		return menuSearch;
	}
	public void setMenuSearch(String menuSearch) {
		this.menuSearch = menuSearch;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getStartNum() {
		return StartNum;
	}
	public void setStartNum(int startNum) {
		StartNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	@Override
	public String toString() {
		return "MenuSearchVO [cateSelect=" + cateSelect + ", menuSearch=" + menuSearch + ", currentPage=" + currentPage
				+ ", StartNum=" + StartNum + ", endNum=" + endNum + "]";
	}
	
	
	



}
