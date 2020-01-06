package kr.co.sist.team2.vo;

public class NotiWriteVO {

	private String noticeWriteSub, noticeWriteContent, noticeWriteType;

	public String getNoticeWriteSub() {
		return noticeWriteSub;
	}

	public void setNoticeWriteSub(String noticeWriteSub) {
		this.noticeWriteSub = noticeWriteSub;
	}

	public String getNoticeWriteContent() {
		return noticeWriteContent;
	}

	public void setNoticeWriteContent(String noticeWriteContent) {
		this.noticeWriteContent = noticeWriteContent;
	}

	public String getNoticeWriteType() {
		return noticeWriteType;
	}

	public void setNoticeWriteType(String noticeWriteType) {
		this.noticeWriteType = noticeWriteType;
	}

	@Override
	public String toString() {
		return "NotiWriteVO [noticeWriteSub=" + noticeWriteSub + ", noticeWriteContent=" + noticeWriteContent
				+ ", noticeWriteType=" + noticeWriteType + "]";
	}


	
	

}//class
