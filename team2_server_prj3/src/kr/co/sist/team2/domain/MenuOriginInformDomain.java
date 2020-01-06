package kr.co.sist.team2.domain;

public class MenuOriginInformDomain {

	private String mName, gdImg, gcName, gdName, gdKal, gdCaffein, gdSugar, gdSalt, gdInfo;
	private int gdPrice;

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getGdImg() {
		return gdImg;
	}

	public void setGdImg(String gdImg) {
		this.gdImg = gdImg;
	}

	public String getGcName() {
		return gcName;
	}

	public void setGcName(String gcName) {
		this.gcName = gcName;
	}

	public String getGdName() {
		return gdName;
	}

	public void setGdName(String gdName) {
		this.gdName = gdName;
	}

	public String getGdKal() {
		return gdKal;
	}

	public void setGdKal(String gdKal) {
		this.gdKal = gdKal;
	}

	public String getGdCaffein() {
		return gdCaffein;
	}

	public void setGdCaffein(String gdCaffein) {
		this.gdCaffein = gdCaffein;
	}

	public String getGdSugar() {
		return gdSugar;
	}

	public void setGdSugar(String gdSugar) {
		this.gdSugar = gdSugar;
	}

	public String getGdSalt() {
		return gdSalt;
	}

	public void setGdSalt(String gdSalt) {
		this.gdSalt = gdSalt;
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

	@Override
	public String toString() {
		return "MenuOriginInformVO [mName=" + mName + ", gdImg=" + gdImg + ", gcName=" + gcName + ", gdName=" + gdName
				+ ", gdKal=" + gdKal + ", gdCaffein=" + gdCaffein + ", gdSugar=" + gdSugar + ", gdSalt=" + gdSalt
				+ ", gdInfo=" + gdInfo + ", gdPrice=" + gdPrice + "]";
	}

}
