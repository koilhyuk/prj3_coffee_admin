package kr.co.sist.team2.vo;

public class QnaListVO {
	private String qCode, mNick, mId, qSubject, qCommFlag, qWriteDate;

	public QnaListVO(String qCode, String mNick, String mId, String qSubject, String qCommFlag, String qWriteDate) {
		this.qCode = qCode;
		this.mNick = mNick;
		this.mId = mId;
		this.qSubject = qSubject;
		this.qCommFlag = qCommFlag;
		this.qWriteDate = qWriteDate;
	}

	public String getqCode() {
		return qCode;
	}

	public void setqCode(String qCode) {
		this.qCode = qCode;
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

	public String getqSubject() {
		return qSubject;
	}

	public void setqSubject(String qSubject) {
		this.qSubject = qSubject;
	}

	public String getqCommFlag() {
		return qCommFlag;
	}

	public void setqCommFlag(String qCommFlag) {
		this.qCommFlag = qCommFlag;
	}

	public String getqWriteDate() {
		return qWriteDate;
	}

	public void setqWriteDate(String qWriteDate) {
		this.qWriteDate = qWriteDate;
	}
	
	
}//QnaListVO
