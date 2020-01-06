package kr.co.sist.team2.domain;

public class MemberOrderListDomain {
	private String gdName, orderDate;
	private int cQuantity;
	
	public String getGdName() {
		return gdName;
	}
	public void setGdName(String gdName) {
		this.gdName = gdName;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getcQuantity() {
		return cQuantity;
	}
	public void setcQuantity(int cQuantity) {
		this.cQuantity = cQuantity;
	}
	@Override
	public String toString() {
		return "MemberOrderListDomain [gdName=" + gdName + ", orderDate=" + orderDate + ", cQuantity=" + cQuantity
				+ "]";
	}
	
	
	
	
	
}//class
