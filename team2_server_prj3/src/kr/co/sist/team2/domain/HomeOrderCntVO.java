package kr.co.sist.team2.domain;

public class HomeOrderCntVO {
	private int preCnt , comCnt;

	public int getPreCnt() {
		return preCnt;
	}

	public void setPreCnt(int preCnt) {
		this.preCnt = preCnt;
	}

	public int getComCnt() {
		return comCnt;
	}

	public void setComCnt(int comCnt) {
		this.comCnt = comCnt;
	}

	@Override
	public String toString() {
		return "HomeOrderCntVO [preCnt=" + preCnt + ", comCnt=" + comCnt + "]";
	}
	
	
	
	

}
