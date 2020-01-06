package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sist.team2.service.MenuService;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.MenuNewAddVO;
import kr.co.sist.team2.vo.MenuNewRecipeVO;
import kr.co.sist.team2.vo.MenuOriginModifyVO;
import kr.co.sist.team2.vo.MenuSearchVO;

@Controller
public class MenuController {
	@RequestMapping(value = "menu_home.do", method = GET)
	public String menuMainForm(Model model,@RequestParam(required = false, defaultValue = "1" ) String current_page, @RequestParam(required = false, defaultValue = "menu_home_search.do")String current_url) {

		MenuService ms = new MenuService();

		MenuSearchVO msVO = new MenuSearchVO();
		msVO.setCateSelect("전체");
		msVO.setMenuSearch("");
		
		msVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ms.totalCount(msVO);

		int pageScale = ms.pageScale();
		int totalPage = ms.totalPage(pageScale, totalCount);

		int startNum = ms.startNum(pageScale, msVO.getCurrentPage());
		int endNum = ms.endNum(pageScale, startNum);
		

		// 계산 된 값으로 조회에 사용될 수 있게 VO에 설정
		msVO.setStartNum(startNum);
		msVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(msVO.getCurrentPage(), totalPage, "menu_home_search.do");
		ms.indexList(ilVO);
		String indexList = ms.indexList(ilVO);
		
		model.addAttribute("menuCateData", ms.searchMenuCate());
		model.addAttribute("menuAllData", ms.searchAllLoading(msVO));
		model.addAttribute("indexList", indexList);

		return "menu/menu_management";
	}// menuMainForm

	@RequestMapping(value = "menu_home_search.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String menuMainSearch(MenuSearchVO msVO,@RequestParam(required = false, defaultValue = "1" ) String current_page, @RequestParam(required = false, defaultValue = "menu_home_search.do")String current_url) {
		
		
		MenuService ms = new MenuService();
		
		msVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ms.totalCount(msVO);

		int pageScale = ms.pageScale();
		int totalPage = ms.totalPage(pageScale, totalCount);

		int startNum = ms.startNum(pageScale, msVO.getCurrentPage());
		int endNum = ms.endNum(pageScale, startNum);
		

		// 계산 된 값으로 조회에 사용될 수 있게 VO에 설정
		msVO.setStartNum(startNum);
		msVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(msVO.getCurrentPage(), totalPage, "menu_home_search.do");
		ms.indexList(ilVO);
		String indexList = ms.indexList(ilVO);
		
		JSONObject joGoodsData = new JSONObject();

		JSONArray jsaGoods = ms.searchAllMenu(msVO);
		
		joGoodsData.put("jsaGoodsData", jsaGoods);
		joGoodsData.put("indexList", indexList);
		
		return joGoodsData.toJSONString();
	}// mainForm

	@RequestMapping(value = "menu_modal_cate.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String menuModalCate() {

		MenuService ms = new MenuService();
		JSONArray jsa = ms.searchAllCateType();

		return jsa.toJSONString();
	}// menuModalCate

	@RequestMapping(value = "new_cate_add.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String newCateAdd(String inputAddCate) {

		MenuService ms = new MenuService();
		JSONObject jo = ms.insertAddCate(inputAddCate);
		return jo.toJSONString();
	}// newCateAdd

	@RequestMapping(value = "add_menu_modal_cate.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String addModalSelectMenu(String selectMenu) {
		MenuService ms = new MenuService();
		JSONArray jsa = ms.searchModalCate(selectMenu);
		return jsa.toJSONString();
	}// newCateAdd

	@RequestMapping(value = "new_menu_add_inform.do", method = POST,produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String newMenuAddInform(MenuNewAddVO mvaVO, @RequestParam("newMenuImgSelect") MultipartFile multipartFile) {
		MenuService ms = new MenuService();
	
		JSONObject jo = ms.insertAddMenu(mvaVO,multipartFile);
		return jo.toJSONString();
	}// newCateAdd

	@RequestMapping(value = "menu_new_recipe.do", method = GET)
	public String insertNewRecipe(MenuNewRecipeVO mnrVO) {
		MenuService ms = new MenuService();

		if (mnrVO.getRecipeMilk() == null) {
			mnrVO.setRecipeMilk("");
		}
		if (mnrVO.getRecipeSyrup() == null) {
			mnrVO.setRecipeSyrup("");
		}
		if (mnrVO.getRecipeTopping() == null) {
			mnrVO.setRecipeTopping("");
		}

		if (ms.insertAddRecipe(mnrVO) > 0) {
		}
		return "redirect:menu_home.do";
	}// menuMainForm

	@RequestMapping(value = "origin_menu_inform.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String originMenuInform(String gdName) {
		MenuService ms = new MenuService();
		JSONObject jo = ms.searchOriginMenu(gdName);
		return jo.toJSONString();
	}// newCateAdd

	@RequestMapping(value = "menu_origin_delete.do", method = GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String menuDeleteProcess(String DeleteGdName) {

		MenuService ms = new MenuService();

		JSONObject jo = ms.deleteGoodsUpdate(DeleteGdName);

		return jo.toJSONString();
	}// menuMainForm

	@RequestMapping(value = "menu_origin_modify.do", method = {GET,POST},produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String menuModifyProcess(MenuOriginModifyVO momVO,@RequestParam("originMenuImgSelect") MultipartFile multipartFileModify) {
		MenuService ms = new MenuService();
		JSONObject jo = ms.originMenuModify(momVO, multipartFileModify);
		

		return jo.toJSONString();
	}// menuMainForm

}// class
