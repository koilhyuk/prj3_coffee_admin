package kr.co.sist.team2.domain;

public class MemberListDomain {
	private int rnum;
	private String mNick, mId, mName, mEmail, mPhone, mReConn, mMemberShipDate;
	
	public MemberListDomain(int rnum, String mNick, String mId, String mName, String mEmail, String mPhone,
			String mReConn, String mMemberShipDate) {
		super();
		this.rnum = rnum;
		this.mNick = mNick;
		this.mId = mId;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mReConn = mReConn;
		this.mMemberShipDate = mMemberShipDate;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getmNick() {
		return mNick;
	}
	public void setmNick(String mNick) {
		this.mNick = mNick;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getmEmail() {
		return mEmail;
	}
	public void setmEmail(String mEmail) {
		this.mEmail = mEmail;
	}
	public String getmPhone() {
		return mPhone;
	}
	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}
	public String getmReConn() {
		return mReConn;
	}
	public void setmReConn(String mReConn) {
		this.mReConn = mReConn;
	}
	public String getmMemberShipDate() {
		return mMemberShipDate;
	}
	public void setmMemberShipDate(String mMemberShipDate) {
		this.mMemberShipDate = mMemberShipDate;
	}

}
