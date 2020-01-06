package kr.co.sist.team2.vo;

public class AdminLoginVO {
	
	private String adminId, adminPass;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPass() {
		return adminPass;
	}

	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}

	@Override
	public String toString() {
		return "AdminLoginVO [adminId=" + adminId + ", adminPass=" + adminPass + "]";
	}

}
