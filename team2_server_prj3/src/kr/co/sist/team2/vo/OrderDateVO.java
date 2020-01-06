package kr.co.sist.team2.vo;

public class OrderDateVO {
	private String frontPeriodSelect, backPeriodSelect;

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

	@Override
	public String toString() {
		return "OrderDateVO [frontPeriodSelect=" + frontPeriodSelect + ", backPeriodSelect=" + backPeriodSelect + "]";
	}

}
