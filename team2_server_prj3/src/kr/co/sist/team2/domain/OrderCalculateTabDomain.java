package kr.co.sist.team2.domain;

public class OrderCalculateTabDomain {
	private String rnum, oCode, cDate, gdName;
	private int cTotalPrice, cQuantity;

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public String getoCode() {
		return oCode;
	}

	public void setoCode(String oCode) {
		this.oCode = oCode;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getGdName() {
		return gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public int getcTotalPrice() {
		return cTotalPrice;
	}

	public void setcTotalPrice(int cTotalPrice) {
		this.cTotalPrice = cTotalPrice;
	}

	public int getcQuantity() {
		return cQuantity;
	}

	public void setcQuantity(int cQuantity) {
		this.cQuantity = cQuantity;
	}

	@Override
	public String toString() {
		return "OrderCalculateTabVO [rnum=" + rnum + ", oCode=" + oCode + ", cDate=" + cDate + ", gdName=" + gdName
				+ ", cTotalPrice=" + cTotalPrice + ", cQuantity=" + cQuantity + "]";
	}

}
