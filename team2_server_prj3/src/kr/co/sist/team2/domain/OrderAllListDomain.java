package kr.co.sist.team2.domain;

public class OrderAllListDomain {
	private int rnum, cTotalPrice;
	private String gdName, cQuantity, oStatus, mId, mPhone, cDate, oCode;

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getcTotalPrice() {
		return cTotalPrice;
	}

	public void setcTotalPrice(int cTotalPrice) {
		this.cTotalPrice = cTotalPrice;
	}

	public String getGdName() {
		return gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public String getcQuantity() {
		return cQuantity;
	}

	public void setcQuantity(String cQuantity) {
		this.cQuantity = cQuantity;
	}

	public String getoStatus() {
		return oStatus;
	}

	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public String getoCode() {
		return oCode;
	}

	public void setoCode(String oCode) {
		this.oCode = oCode;
	}

	@Override
	public String toString() {
		return "OrderAllListDomain [rnum=" + rnum + ", cTotalPrice=" + cTotalPrice + ", gdName=" + gdName
				+ ", cQuantity=" + cQuantity + ", oStatus=" + oStatus + ", mId=" + mId + ", mPhone=" + mPhone
				+ ", cDate=" + cDate + ", oCode=" + oCode + "]";
	}

}
