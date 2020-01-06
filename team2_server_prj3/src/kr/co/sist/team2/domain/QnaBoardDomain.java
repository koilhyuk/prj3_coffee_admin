package kr.co.sist.team2.domain;

public class QnaBoardDomain {
	private int rnum;
	private String q_code, m_id, q_subject, q_comm_flag, q_comm_date, q_write_date;
	public QnaBoardDomain() {
		super();
	}
	public QnaBoardDomain(int rnum, String q_code, String m_id, String q_subject, String q_comm_flag,
			String q_comm_date, String q_write_date) {
		super();
		this.rnum = rnum;
		this.q_code = q_code;
		this.m_id = m_id;
		this.q_subject = q_subject;
		this.q_comm_flag = q_comm_flag;
		this.q_comm_date = q_comm_date;
		this.q_write_date = q_write_date;
	}
	public int getRnum() {
		return rnum;
	}
	public void setRnum(int rnum) {
		this.rnum = rnum;
	}
	public String getQ_code() {
		return q_code;
	}
	public void setQ_code(String q_code) {
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
	public String getQ_comm_date() {
		return q_comm_date;
	}
	public void setQ_comm_date(String q_comm_date) {
		this.q_comm_date = q_comm_date;
	}
	public String getQ_write_date() {
		return q_write_date;
	}
	public void setQ_write_date(String q_write_date) {
		this.q_write_date = q_write_date;
	}
	@Override
	public String toString() {
		return "QnaBoardDomain [rnum=" + rnum + ", q_code=" + q_code + ", m_id=" + m_id + ", q_subject=" + q_subject
				+ ", q_comm_flag=" + q_comm_flag + ", q_comm_date=" + q_comm_date + ", q_write_date=" + q_write_date
				+ "]";
	}
	
	

	
}
