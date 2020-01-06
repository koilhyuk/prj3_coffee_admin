package kr.co.sist.team2.vo;

public class MenuNewAddVO {
	private String modalSelectCate, newMenuImgselect, gdName, gdInfo;
	private int gdPrice, gdKcal, gdCaffein, gdSugar, gdSalt;

	public String getModalSelectCate() {
		return modalSelectCate;
	}

	public void setModalSelectCate(String modalSelectCate) {
		this.modalSelectCate = modalSelectCate;
	}

	public String getNewMenuImgselect() {
		return newMenuImgselect;
	}

	public void setNewMenuImgselect(String newMenuImgselect) {
		this.newMenuImgselect = newMenuImgselect;
	}

	public String getGdName() {
		return gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public String getGdInfo() {
		return gdInfo;
	}

	public void setGdInfo(String gdInfo) {
		this.gdInfo = gdInfo;
	}

	public int getGdPrice() {
		return gdPrice;
	}

	public void setGdPrice(int gdPrice) {
		this.gdPrice = gdPrice;
	}

	public int getGdKcal() {
		return gdKcal;
	}

	public void setGdKcal(int gdKcal) {
		this.gdKcal = gdKcal;
	}

	public int getGdCaffein() {
		return gdCaffein;
	}

	public void setGdCaffein(int gdCaffein) {
		this.gdCaffein = gdCaffein;
	}

	public int getGdSugar() {
		return gdSugar;
	}

	public void setGdSugar(int gdSugar) {
		this.gdSugar = gdSugar;
	}

	public int getGdSalt() {
		return gdSalt;
	}

	public void setGdSalt(int gdSalt) {
		this.gdSalt = gdSalt;
	}

	@Override
	public String toString() {
		return "MenuNewAddVO [modalSelectCate=" + modalSelectCate + ", newMenuImgselect=" + newMenuImgselect
				+ ", gdName=" + gdName + ", gdInfo=" + gdInfo + ", gdPrice=" + gdPrice + ", gdKcal=" + gdKcal
				+ ", gdCaffein=" + gdCaffein + ", gdSugar=" + gdSugar + ", gdSalt=" + gdSalt + "]";
	}

}
