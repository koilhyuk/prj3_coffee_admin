package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.team2.domain.NoticeDetailDomain;
import kr.co.sist.team2.domain.NoticeListDomain;
import kr.co.sist.team2.service.NoticeService;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.NotiUpdateVO;
import kr.co.sist.team2.vo.NotiWriteVO;
import kr.co.sist.team2.vo.SearchVO;

@Controller
public class NoticeController {
	@RequestMapping(value = "notice_home.do", method = GET)
	public String noticeMainForm(SearchVO sVO, @RequestParam(required = false, defaultValue = "1") String current_page, 
			 String field,  String keyword, Model model) {
		
		NoticeService ns = new NoticeService();
		
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ns.totalCount(sVO);
		int pageScale = ns.pageScale();
		int totalPage = ns.totalPage(pageScale, totalCount);
		int startNum = ns.startNum(pageScale, sVO.getCurrentPage()==0?1:sVO.getCurrentPage());
		int endNum = ns.endNum(pageScale, startNum);
		
		//계산된 값으로 조회에 사용할 수 있게 VO에 설정
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		IndexListVO ilVO = null;
		if (field == null && keyword == null) {
			ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "notice_home.do");
		}else {
			ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "notice_home.do?field="+field+"&keyword="+keyword+"&");
		}
		ns.indexList(ilVO);
		
		String indexList = ns.indexList(ilVO);
		
		List<NoticeListDomain> notiList = ns.searchNoticeList(sVO);
		
		model.addAttribute("notiBoardList", notiList);
		model.addAttribute("indexList", indexList);
		
		return "notice/notice_evt";
		
	}// mainForm
	
	@RequestMapping(value = "noti_form.do", method = GET)
	public String notiForm(Model model) {
		
		
		return "notice/notice_evt_form";
	}//notiForm
	
	@RequestMapping(value = "noti_process.do", method = GET)
	public String notiProcess(String nCode, Model model) {
		NoticeService ns = new NoticeService();
		
		NoticeDetailDomain ndd = ns.searchNotiDetail(nCode);
		
		model.addAttribute("noticeDetailData", ndd);
		
		return "notice/notice_evt_process";
	}//notiProcess
	
	@RequestMapping(value = "noti_write_process.do", method = {POST,GET})
	public String notiWriteProcess(NotiWriteVO nwVO, Model model) {
		NoticeService ns = new NoticeService();
		boolean flag = ns.addNoti(nwVO);
		
		model.addAttribute("insertFlag", flag);
		
		return "notice/notice_write_process";
	}//notiWriteProcess
	
	
	@RequestMapping(value = "noti_write_update.do", method = POST)
	public String modifyNotiData(NotiUpdateVO nuVO,String notiSelect, Model model) {
		NoticeService ns = new NoticeService();
		boolean flag = ns.modifyNoti(nuVO);
		model.addAttribute("updateFlag", flag);
		
		return "redirect:/notice_home.do";
	}//modifyNotiData
	
	@RequestMapping(value = "remove_notice.do", method = POST)
	public String deleteNotiData(String noticeCode, Model model) {
		NoticeService ns = new NoticeService();
		boolean flag = ns.removeNotice(noticeCode);
		model.addAttribute("deleteFlag", flag);
		
		return "redirect:/notice_home.do";
	}//deleteNotiData
	
}//class
