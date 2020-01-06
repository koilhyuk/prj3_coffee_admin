package kr.co.sist.team2.domain;

public class MenuOriginRecipeDomain {
	private String brMilk, brSyrup, brTopping, brType;
	private int brShot, brCream;

	public MenuOriginRecipeDomain() {
		this.brMilk = "";
		this.brSyrup = "";
		this.brTopping = "";
	}

	public String getBrMilk() {
		if (brMilk == null) {
			brMilk = "";
		}
		return brMilk;
	}

	public void setBrMilk(String brMilk) {
		this.brMilk = brMilk;
	}

	public String getBrSyrup() {
		if (brSyrup == null) {
			brSyrup = "";
		}
		return brSyrup;
	}

	public void setBrSyrup(String brSyrup) {
		this.brSyrup = brSyrup;
	}

	public String getBrTopping() {
		if (brTopping == null) {
			brTopping = "";
		}
		return brTopping;
	}

	public void setBrTopping(String brTopping) {
		this.brTopping = brTopping;
	}

	public String getBrType() {
		return brType;
	}

	public void setBrType(String brType) {
		this.brType = brType;
	}

	public int getBrShot() {
		return brShot;
	}

	public void setBrShot(int brShot) {
		this.brShot = brShot;
	}

	public int getBrCream() {
		return brCream;
	}

	public void setBrCream(int brCream) {
		this.brCream = brCream;
	}

}
