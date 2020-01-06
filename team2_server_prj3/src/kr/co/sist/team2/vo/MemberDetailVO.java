package kr.co.sist.team2.vo;

import java.util.List;

public class MemberDetailVO {

	private String mNick, mId, mName, mEmail, mPhone, mReConn, mMembershipDate;
	private int lemon;
	List<PointListiVO> pointList;
	
	public MemberDetailVO(String mNick, String mId, String mName, String mEmail, String mPhone, String mReConn,
			String mMembershipDate, int lemon, List<PointListiVO> pointList) {
		super();
		this.mNick = mNick;
		this.mId = mId;
		this.mName = mName;
		this.mEmail = mEmail;
		this.mPhone = mPhone;
		this.mReConn = mReConn;
		this.mMembershipDate = mMembershipDate;
		this.lemon = lemon;
		this.pointList = pointList;
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
	public String getmMembershipDate() {
		return mMembershipDate;
	}
	public void setmMembershipDate(String mMembershipDate) {
		this.mMembershipDate = mMembershipDate;
	}
	public int getLemon() {
		return lemon;
	}
	public void setLemon(int lemon) {
		this.lemon = lemon;
	}
	public List<PointListiVO> getPointList() {
		return pointList;
	}
	public void setPointList(List<PointListiVO> pointList) {
		this.pointList = pointList;
	}
	
}
