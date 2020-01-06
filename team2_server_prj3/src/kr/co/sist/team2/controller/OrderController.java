package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.sist.team2.service.OrderService;
import kr.co.sist.team2.vo.OrderSearchCommVO;

@Controller
public class OrderController {
	@RequestMapping(value = "order_home.do", method = GET)
	public String orderMainForm(Model model, OrderSearchCommVO odcVO) {

		OrderService os = new OrderService();
		odcVO.setSearchInput("");
		odcVO.setSelectStatus("A");
		odcVO.setFrontPeriodSelect("1111-11-11");
		odcVO.setBackPeriodSelect("1111-11-11");

		model.addAttribute("searchAllOrder", os.searchAllOrderData(odcVO));
		model.addAttribute("searchAllCalculate", os.searchAllCalculate(odcVO));
		model.addAttribute("searchCalMoney", os.searchAllMoney(odcVO));
		return "order/order_management";
	}// mainForm

	@RequestMapping(value = "order_ajax_search.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String orderAjaxMainProcess(Model model, OrderSearchCommVO odcVO) {
		JSONArray jsa = null;
		OrderService os = new OrderService();

		jsa = os.searchAjaxOrderData(odcVO);

		return jsa.toJSONString();
	}// orderAjaxMainProcess

	@RequestMapping(value = "order_ajax_modify.do", method = POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String orderAjaxModifyProcess(@RequestParam(value = "codeO") List<String> codeO) {
		OrderService os = new OrderService();
		JSONObject joFlag = os.ajaxOrderStatusModify(codeO);


		return joFlag.toJSONString();
	}// orderAjaxMainProcess

	@RequestMapping(value = "order_ajax_money.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String orderAjaxMoneyProcess(OrderSearchCommVO odcVO) {
		
		OrderService os = new OrderService();
		JSONObject totalJsonObject = new JSONObject();

		JSONArray jsa = os.searchAjaxAllCalculate(odcVO);
		JSONObject jo =os.searchAllAjaxMoney(odcVO);
		
		totalJsonObject.put("tableData", jsa);
		totalJsonObject.put("moneyData", jo);
		return totalJsonObject.toJSONString();
	}// orderAjaxMainProcess

}// OrderController
