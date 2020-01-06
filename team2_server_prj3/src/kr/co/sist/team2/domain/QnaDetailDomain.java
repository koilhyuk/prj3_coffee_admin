package kr.co.sist.team2.domain;

public class QnaDetailDomain {
	
	private String m_id, q_subject, q_content, q_answer, q_write_date;

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getQ_subject() {
		return q_subject;
	}

	public void setQ_subject(String q_subject) {
		this.q_subject = q_subject;
	}

	public String getQ_content() {
		return q_content;
	}

	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}

	public String getQ_answer() {
		return q_answer;
	}

	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}

	public String getQ_write_date() {
		return q_write_date;
	}

	public void setQ_write_date(String q_write_date) {
		this.q_write_date = q_write_date;
	}

	@Override
	public String toString() {
		return "QnaDetailDomain [m_id=" + m_id + ", q_subject=" + q_subject + ", q_content=" + q_content + ", q_answer="
				+ q_answer + ", q_write_date=" + q_write_date + "]";
	}

}
