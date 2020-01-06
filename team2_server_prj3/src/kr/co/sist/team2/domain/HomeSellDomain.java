package kr.co.sist.team2.domain;

public class HomeSellDomain {
	int daySell, monthSell;

	public int getDaySell() {
		return daySell;
	}

	public void setDaySell(int daySell) {
		this.daySell = daySell;
	}

	public int getMonthSell() {
		return monthSell;
	}

	public void setMonthSell(int monthSell) {
		this.monthSell = monthSell;
	}

	@Override
	public String toString() {
		return "HomeSellDomain [daySell=" + daySell + ", monthSell=" + monthSell + "]";
	}

}
