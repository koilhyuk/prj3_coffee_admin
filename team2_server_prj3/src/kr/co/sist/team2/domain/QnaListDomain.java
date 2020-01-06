package kr.co.sist.team2.domain;

public class QnaListDomain {
	private int q_code;
	private String m_id, q_subject, q_comm_flag, q_write_date;
	
	public int getQ_code() {
		return q_code;
	}
	public void setQ_code(int q_code) {
		this.q_code = q_code;
	}
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
	public String getQ_comm_flag() {
		return q_comm_flag;
	}
	public void setQ_comm_flag(String q_comm_flag) {
		this.q_comm_flag = q_comm_flag;
	}
	public String getQ_write_date() {
		return q_write_date;
	}
	public void setQ_write_date(String q_write_date) {
		this.q_write_date = q_write_date;
	}
	@Override
	public String toString() {
		return "QnaListDomain [q_code=" + q_code + ", m_id=" + m_id + ", q_subject=" + q_subject + ", q_comm_flag="
				+ q_comm_flag + ", q_write_date=" + q_write_date + "]";
	}
	
	
}
