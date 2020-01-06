package kr.co.sist.team2.vo;

public class QnaDetailVO {
	
	private String q_code, q_answer;

	public QnaDetailVO() {
		super();
	}

	public QnaDetailVO(String q_code, String q_answer) {
		super();
		this.q_code = q_code;
		this.q_answer = q_answer;
	}

	public String getQ_code() {
		return q_code;
	}

	public void setQ_code(String q_code) {
		this.q_code = q_code;
	}

	public String getQ_answer() {
		return q_answer;
	}

	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}

	@Override
	public String toString() {
		return "QnaDetailVO [q_code=" + q_code + ", q_answer=" + q_answer + "]";
	}
	
	

	
}
