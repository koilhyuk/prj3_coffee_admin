package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.team2.domain.QnaBoardDomain;
import kr.co.sist.team2.domain.QnaDetailDomain;
import kr.co.sist.team2.service.QnaService;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.QnaDetailVO;
import kr.co.sist.team2.vo.SearchQnaVO;

@Controller
public class QnaController {
	
	/**
	 * Qna글 검색
	 * @param sqVO
	 * @param current_page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "qna_home.do", method = {GET,POST})
	public String searchQnaList(SearchQnaVO sqVO, @RequestParam(required = false, defaultValue = "1")String current_page, @RequestParam(required = false, defaultValue = "")String keyword, Model model) {
		QnaService qs=new QnaService();
		//indexList에서 제공하는 url인 current_page가 조회에 사용되는 sVO의 이름과 다르므로 current_page를 파라메터로 받고 sVO에 설정
		sqVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = qs.totalCount(sqVO);
		int pageScale = qs.pageScale();
		int totalPage = qs.totalPage(pageScale, totalCount);
		int startNum = qs.StartNum(pageScale, sqVO.getCurrentPage());
		int endNum = qs.endNum(pageScale, startNum);
		
		
		//계산된 값으로 조회에 사용될 수 있게 VO에 설정
		sqVO.setStartNum(startNum);
		sqVO.setEndNum(endNum);
		
		IndexListVO ilVO = new IndexListVO(sqVO.getCurrentPage(), totalPage, "qna_home.do");
		
		String indexList = qs.indexList(ilVO);
		List<QnaBoardDomain> qList = qs.searchQnaList(sqVO);
		
		
		model.addAttribute("searchQnaList", qList);
		model.addAttribute("indexList", indexList);				
		
		return "qna/qna";
	}//searchQnaList
	
	@RequestMapping(value="qna_detail.do", method=GET)
	public String qnaDetail(String code,Model model) {
		QnaService qs=new QnaService();
		QnaDetailDomain qdd=qs.searchQnaDetail(code);
		model.addAttribute("qnaDetail",qdd);
		return "qna/qna_detail";
	}
	
	@RequestMapping(value = "updateQnaAdmin.do", method = POST)
	public String updateQna(QnaDetailVO qdVO,Model model) {
	

		QnaService qs=new QnaService();

		qs.updateQnaAnswer(qdVO);
		
		return "redirect:qna_home.do";
	}
	
	@RequestMapping(value="qna_delete.do", method=GET)
	public String deleteQna(String code, Model model) {
		//삭제하기 
		QnaService qs=new QnaService();
		qs.deleteQna(code);
		return "redirect:qna_home.do";
	}
	
	
}//class
