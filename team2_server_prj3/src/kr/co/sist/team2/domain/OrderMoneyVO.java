package kr.co.sist.team2.domain;

public class OrderMoneyVO {
	private int totalMoney, periodMoney;

	public int getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(int totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getPeriodMoney() {
		return periodMoney;
	}

	public void setPeriodMoney(int periodMoney) {
		this.periodMoney = periodMoney;
	}

	@Override
	public String toString() {
		return "OrderMoneyVO [totalMoney=" + totalMoney + ", periodMoney=" + periodMoney + "]";
	}

}
