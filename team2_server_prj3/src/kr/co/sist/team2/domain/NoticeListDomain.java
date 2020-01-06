package kr.co.sist.team2.domain;

public class NoticeListDomain {
	private int rnum;
	private String nCode, ntCode, nSub, nDate;
	
	public NoticeListDomain(int rnum, String nCode, String ntCode, String nSub, String nDate) {
		super();
		this.rnum = rnum;
		this.nCode = nCode;
		this.ntCode = ntCode;
		this.nSub = nSub;
		this.nDate = nDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getnCode() {
		return nCode;
	}
	public void setnCode(String nCode) {
		this.nCode = nCode;
	}
	public String getNtCode() {
		return ntCode;
	}
	public void setNtCode(String ntCode) {
		this.ntCode = ntCode;
	}
	public String getnSub() {
		return nSub;
	}
	public void setnSub(String nSub) {
		this.nSub = nSub;
	}
	public String getnDate() {
		return nDate;
	}
	public void setnDate(String nDate) {
		this.nDate = nDate;
	}
	@Override
	public String toString() {
		return "NoticeListDomain [rnum=" + rnum + ", nCode=" + nCode + ", ntCode=" + ntCode + ", nSub=" + nSub
				+ ", nDate=" + nDate + "]";
	}
	
	
	
}//class
