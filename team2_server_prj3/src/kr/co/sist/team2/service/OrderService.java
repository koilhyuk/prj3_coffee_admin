package kr.co.sist.team2.service;

import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.co.sist.team2.dao.OrderManageDAO;
import kr.co.sist.team2.domain.OrderAllListDomain;
import kr.co.sist.team2.domain.OrderCalculateTabDomain;
import kr.co.sist.team2.domain.OrderMoneyVO;
import kr.co.sist.team2.vo.OrderSearchCommVO;

public class OrderService {

	public List<OrderAllListDomain> searchAllOrderData(OrderSearchCommVO odcVO) {
		List<OrderAllListDomain> orderDatas = null;

		OrderManageDAO omDAO = OrderManageDAO.getInstance();

		try {
			orderDatas = omDAO.selectAllOrderData(odcVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderDatas;
	}// searchAllOrderData

	public List<OrderCalculateTabDomain> searchAllCalculate(OrderSearchCommVO odcVO) {
		List<OrderCalculateTabDomain> orderTabDatas = null;
		OrderManageDAO omDAO = OrderManageDAO.getInstance();
		try {
			orderTabDatas = omDAO.selectOrderTab(odcVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderTabDatas;
	}// searchAllCalculate
	
	public JSONArray searchAjaxAllCalculate(OrderSearchCommVO odcVO) {
		JSONArray jsa = new JSONArray();
		JSONObject joData= null;
		List<OrderCalculateTabDomain> orderTabDatas = null;
		OrderManageDAO omDAO = OrderManageDAO.getInstance();
		try {
			orderTabDatas = omDAO.selectOrderTab(odcVO);
			
			OrderCalculateTabDomain octd = null;
			for(int i =0; i<orderTabDatas.size();i++) {
				octd = orderTabDatas.get(i);
				joData = new JSONObject();
				joData.put("rnum", octd.getRnum());
				joData.put("oCode", octd.getoCode());
				joData.put("cDate", octd.getcDate());
				joData.put("gdName", octd.getGdName());
				joData.put("cTotalPrice", octd.getcTotalPrice());
				joData.put("cQuantity", octd.getcQuantity());
				
				jsa.add(joData);
			}// end for
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jsa;
	}// searchAjaxAllCalculate
	
	
	public OrderMoneyVO searchAllMoney(OrderSearchCommVO odcVO) {
		OrderMoneyVO omVO= new OrderMoneyVO();
		OrderManageDAO omDAO = OrderManageDAO.getInstance();
		
		try {
			odcVO.setSearchFlag("Tot");
			omVO.setTotalMoney(omDAO.selectOrderMoney(odcVO));
			
			if (!"1111-11-11".equals(odcVO.getFrontPeriodSelect()) && !"1111-11-11".equals(odcVO.getBackPeriodSelect())) {// 기간별 정산할 때
				odcVO.setSearchFlag("Peri");
				omVO.setPeriodMoney(omDAO.selectOrderMoney(odcVO));
			}// end if
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return omVO;
	}// searchAllCalculate
	
	public JSONObject searchAllAjaxMoney(OrderSearchCommVO odcVO) {
		OrderMoneyVO omVO= new OrderMoneyVO();
		JSONObject joMoney = new JSONObject();
		OrderManageDAO omDAO = OrderManageDAO.getInstance();
		
		try {
			odcVO.setSearchFlag("Tot");
			omVO.setTotalMoney(omDAO.selectOrderMoney(odcVO));
			joMoney.put("totalMoney", omVO.getTotalMoney());
			
			if (!"1111-11-11".equals(odcVO.getFrontPeriodSelect()) && !"1111-11-11".equals(odcVO.getBackPeriodSelect())) {// 기간별 정산할 때
				odcVO.setSearchFlag("Peri");
				omVO.setPeriodMoney(omDAO.selectOrderMoney(odcVO));
				joMoney.put("periodMoney", omVO.getPeriodMoney());
			}// end if
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joMoney;
	}// searchAllAjaxMoney

	
	
	
	public JSONArray searchAjaxOrderData(OrderSearchCommVO odcVO) {
		List<OrderAllListDomain> orderDatas = null;
		JSONArray jsaOrder = new JSONArray();
		JSONObject jsOrder = null;

		OrderManageDAO omDAO = OrderManageDAO.getInstance();

		try {
			orderDatas = omDAO.selectAllOrderData(odcVO);

			for (int i = 0; i < orderDatas.size(); i++) {
				OrderAllListDomain oad = orderDatas.get(i);
				jsOrder = new JSONObject();
				jsOrder.put("rnum", oad.getRnum());
				jsOrder.put("cTotalPrice", oad.getcTotalPrice());
				jsOrder.put("gdName", oad.getGdName());
				jsOrder.put("cQuantity", oad.getcQuantity());
				jsOrder.put("oStatus", oad.getoStatus());
				jsOrder.put("mId", oad.getmId());
				jsOrder.put("mPhone", oad.getmPhone());
				jsOrder.put("cDate", oad.getcDate());
				jsOrder.put("oCode", oad.getoCode());

				jsaOrder.add(jsOrder);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jsaOrder;
	}// searchAjaxOrderData

	public JSONObject ajaxOrderStatusModify(List<String> codeO) {
		boolean updateFlag = false;
		JSONObject joFlag = new JSONObject();
		OrderManageDAO omDAO = OrderManageDAO.getInstance();

		String tempCode = null;

		try {
			for (int i = 0; i < codeO.size(); i++) {
				tempCode = codeO.get(i);
				updateFlag = (omDAO.updateOrderStatus(tempCode) > 0);
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		joFlag.put("updateFlag", updateFlag);

		return joFlag;
	}// ajaxOrderStatusModify

}// class
