package kr.co.sist.team2.vo;

public class NotiUpdateVO {
	private String noticeWriteSub, noticeWriteContent, noticeCode;


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

	public String getNoticeCode() {
		return noticeCode;
	}

	public void setNoticeCode(String noticeCode) {
		this.noticeCode = noticeCode;
	}

	@Override
	public String toString() {
		return "NotiUpdateVO [noticeWriteSub=" + noticeWriteSub + ", noticeWriteContent=" + noticeWriteContent
				+ ", noticeCode=" + noticeCode + "]";
	}
	
}//class
