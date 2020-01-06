package kr.co.sist.team2.vo;

public class OrderSearchCommVO {
	private String searchInput, selectStatus, frontPeriodSelect, backPeriodSelect, searchFlag;

	public String getSearchInput() {
		return searchInput;
	}

	public void setSearchInput(String searchInput) {
		this.searchInput = searchInput;
	}

	public String getSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(String selectStatus) {
		this.selectStatus = selectStatus;
	}

	public String getFrontPeriodSelect() {
		return frontPeriodSelect;
	}

	public void setFrontPeriodSelect(String frontPeriodSelect) {
		this.frontPeriodSelect = frontPeriodSelect;
	}

	public String getBackPeriodSelect() {
		return backPeriodSelect;
	}

	public void setBackPeriodSelect(String backPeriodSelect) {
		this.backPeriodSelect = backPeriodSelect;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	@Override
	public String toString() {
		return "OrderSearchCommVO [searchInput=" + searchInput + ", selectStatus=" + selectStatus
				+ ", frontPeriodSelect=" + frontPeriodSelect + ", backPeriodSelect=" + backPeriodSelect
				+ ", searchFlag=" + searchFlag + "]";
	}

	
}
