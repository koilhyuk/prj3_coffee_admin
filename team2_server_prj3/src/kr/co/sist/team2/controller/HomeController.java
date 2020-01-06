package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.team2.domain.HomeNoticeDomain;
import kr.co.sist.team2.service.MainService;
import kr.co.sist.team2.vo.HomeNoticeModifyVO;
import kr.co.sist.team2.vo.HomeNoticeNewInsertVO;
import kr.co.sist.team2.vo.HomeQnaCommVO;

@Controller
public class HomeController {

	@RequestMapping(value = "main_home.do", method = GET)
	public String mainForm(Model model) {
		
		MainService ms = new MainService();

		model.addAttribute("homeSellData", ms.searchHomeSell());
		model.addAttribute("homeOrderCntData", ms.searchHomeOrderCnt());
		model.addAttribute("homeNoticeData", ms.searchHomeNotice());
		model.addAttribute("homeQnaData", ms.searchHomeQna());
		
		HomeNoticeDomain test = null;
		for (int i = 0; i < ms.searchHomeNotice().size(); i++) {
			test = ms.searchHomeNotice().get(i);
		}
		return "home/main";
	}// mainForm

	@RequestMapping(value = "notice_write.do", method = { GET, POST })
	public String mainNoticeWrite(HttpServletRequest request,Model model, @RequestParam(required = false) String nCode) {
		
		
		if (nCode != null) {// 있는 이벤트나 공지사항 수정할 때
			MainService ms = new MainService();
			model.addAttribute("selectNoticeDetData", ms.searchHomeNoticeDet(nCode));
			model.addAttribute("noticeWriteFlag", "M");
		} // end if
		return "notice/notice_evt_detail";
	}// mainNoticeWrite

	@RequestMapping(value = "qna_write.do", method = GET)
	public String mainQnaWrite() {
		return "qna/qna_detaill";
	}// mainQnaWrite

	@RequestMapping(value = "qna_home_detail.do", method = POST)
	public String mainQnaDetail(String qCode, Model model) {
		MainService ms = new MainService();
		model.addAttribute("searchQnaData", ms.searchHomeQnaDet(qCode));
		model.addAttribute("inputFlag", "H");
		return "qna/qna_detaill";
	}// mainQnaWrite

	@RequestMapping(value = "write_qna_comm.do", method = POST)
	public String qnaWriteComm(Model model, HomeQnaCommVO hqcVO) {
		MainService ms = new MainService();
		int flag = 0;
		String url = "qna/qna";
		flag = ms.HomeQnaComm(hqcVO);
		if (flag == 0) {// 실패
			url = "home/main";
		}
		return url;
	}// mainQnaWrite

	@RequestMapping(value = "notice_home_modify.do", method = POST)
	public String noticeHomeModify(Model model, HomeNoticeModifyVO hnmVO) {
		MainService ms = new MainService();
		int flag = 0;
		flag = ms.HomeNoticeModify(hnmVO);

		if (flag == 0) {// 실패
		}

		return "redirect:notice_home.do";
	}// mainQnaWrite

	@RequestMapping(value = "notice_home_new_write.do", method = POST)
	public String noticeHomeNewWrite(Model model, HomeNoticeNewInsertVO hnniVO) {
		MainService ms = new MainService();
		int flag = 0;
		String url = "notice/notice_evt";
		flag = ms.HomeNoticeInsert(hnniVO);

		if (flag == 0) {// 실패
			url = "home/main";
		}
		return url;

	}// noticeHomeNewWrite

}// class
