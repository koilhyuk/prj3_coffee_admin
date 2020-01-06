package kr.co.sist.team2.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.sist.team2.domain.MemberDetailDomain;
import kr.co.sist.team2.domain.MemberListDomain;
import kr.co.sist.team2.domain.MemberOrderListDomain;
import kr.co.sist.team2.service.MemberService;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.PointListiVO;
import kr.co.sist.team2.vo.SearchVO;

@Controller
public class MemberController {
	@RequestMapping(value = "member_home.do", method = GET)
	public String memberMainProcess(SearchVO sVO, @RequestParam(required = false, defaultValue = "1") String current_page, 
		String field, String keyword, Model model) {
		
		MemberService ms = new MemberService();
		//indexList에서 제공하는 url인 current_page가 조회에 사용되는 sVO의 이름과
		//다르기 때문에 current_page를 파라메터로 받고 sVO에 설정하여 조회한다.
		sVO.setCurrentPage(Integer.parseInt(current_page));
		
		int totalCount = ms.totalCount(sVO);
		int pageScale = ms.pageScale();
		int totalPage = ms.totalPage(pageScale, totalCount);
		int startNum = ms.startNum(pageScale, sVO.getCurrentPage()==0?1:sVO.getCurrentPage());
		int endNum = ms.endNum(pageScale, startNum);
		
		//계산된 값으로 조회에 사용할 수 있게 VO에 설정
		sVO.setStartNum(startNum);
		sVO.setEndNum(endNum);
		
		
		IndexListVO ilVO = null;
		if(field == null && keyword == null) {
			ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "member_home.do");
		}else {
			ilVO = new IndexListVO(sVO.getCurrentPage(), totalPage, "member_home.do?field="+field+"&keyword="+keyword+"&");
		}//end else
		ms.indexList(ilVO);
		
		String indexList = ms.indexList(ilVO);
		
		List<MemberListDomain> memAllList = ms.searchMemList(sVO);
		
		model.addAttribute("memBoardList", memAllList);
		model.addAttribute("indexList", indexList);
		
		return "member/member_management";
	}// mainForm
	
	@RequestMapping(value = "member_process.do", method = GET)
	public String searchMemberProcess(String mId, Model model) {
		
		MemberService ms = new MemberService();
		MemberDetailDomain mdd = ms.searchMemDetail(mId);
		List<MemberOrderListDomain> mold = ms.searchMemOrder(mId);
		List<PointListiVO> plVo = ms.searchMemPoint(mId);
		int mPoint = ms.countMPoint(mId);
		
		model.addAttribute("memberInfo", mdd);
		model.addAttribute("memberOrder", mold);
		model.addAttribute("memberPoint", plVo);
		model.addAttribute("mPoint", mPoint);
		
		return "member/member_detail";
	}//searchMemberProcess
	
	@RequestMapping(value = "remove_member.do", method = POST)
	public String removeMemberData(String mId, Model model) {
		
		MemberService ms = new MemberService();
		boolean flag = ms.removeMember(mId);
		model.addAttribute("deleteFlag", flag);
		
		return "redirect:/member_home.do";
	}
	
}//class
