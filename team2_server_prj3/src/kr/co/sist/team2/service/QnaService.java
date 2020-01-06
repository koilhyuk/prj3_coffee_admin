package kr.co.sist.team2.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.team2.dao.QnaDAO;
import kr.co.sist.team2.domain.QnaBoardDomain;
import kr.co.sist.team2.domain.QnaDetailDomain;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.QnaDetailVO;
import kr.co.sist.team2.vo.SearchQnaVO;

public class QnaService {
	
	/**
	 * 검색 값에 따른 전체글의 개수 조회
	 * @param sqVO
	 * @return
	 */
	public int totalCount(SearchQnaVO sqVO) {
		int totalCount=0;
		QnaDAO qDao = QnaDAO.getInstance();
		
		try {
			totalCount = qDao.selectTotalCount(sqVO);			
		} catch (Exception e) {
			e.printStackTrace();
		}//end catch
		
		return totalCount;		
	}//totalCount
	
	/**
	 * Qna리스트 한 페이지에 보여줄 Qna글 개수
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		
		return pageScale;		
	}//pageScale
	
	/**
	 * 총 페이지 개수
	 * @param pageScale
	 * @param totalCount
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount/pageScale;
		
		if(totalCount%pageScale != 0) {
			totalPage++;
		}//end if
		
		return totalPage;	
	}//totalPage
	
	/**
	 * 페이지의 시작 번호
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int StartNum(int pageScale, int currentPage) {
		int startNum = currentPage * pageScale - pageScale +1;		
		
		return startNum;
	}//StartNum
	
	/**
	 * 페이지의 끝 번호를 구하는 일
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;
		
		return endNum;
	}//endNum
	
	
	public String indexList(IndexListVO ilVO) {
		int pagenumber; // 화면에 보여질 페이지 인덱스 수
		int startpage; // 화면에 보여질 시작페이지 번호
		int endpage; // 화면에 보여질 마지막페이지 번호
		int curpage; // 이동하고자 하는 페이지 번호

		String strList=""; // 리턴될 페이지 인덱스 리스트

		pagenumber = 10; // 한 화면의 페이지 인덱스 수 

		// 시작 페이지번호 구하기
		startpage = ((ilVO.getCurrentPage()- 1) / pagenumber) * pagenumber + 1;

		// 마지막 페이지번호 구하기
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;

		// 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우 

		// 총 페이지 수가 마지막페이지 번호가 됨


		if (ilVO.getTotalPage()<= endpage){
			endpage = ilVO.getTotalPage();
		}//end if

		// 첫번째 페이지 인덱스 화면이 아닌경우
		if ( ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // 시작페이지 번호보다 1 적은 페이지로 이동
			strList = strList + "<li class=\"page-item\"><a class=\"page-link\" href="+ilVO.getUrl()+"?current_page="+curpage+" aria-label=\"Previous\">\r\n" + 
					"		        <span aria-hidden=\"true\">&laquo;</span></a></li>";
		}else{
			strList = strList + "<li class=\"page-item\">\r\n" + 
					"		      <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">\r\n" + 
					"		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + 
					"		      </a>\r\n" + 
					"		    </li>";
		}

//		strList = strList + " ... ";

		// 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
		curpage = startpage;

		while (curpage <= endpage){
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='현재페이지'>"+ilVO.getCurrentPage()+"</a></li>";
			} else {
				strList = strList +"<li class=\"page-item\"><a class=\"page-link\" href="+ilVO.getUrl()+"?current_page="+curpage+">"+curpage+"</a></li>";
			}//end else

			curpage++;
		}//end while

//		strList = strList + " ... ";

		// 뒤에 페이지가 더 있는경우
		if ( ilVO.getTotalPage() > endpage) {
			curpage = endpage + 1; 
			strList = strList + "<a href="+ilVO.getUrl()+"?current_page="+curpage+" aria-label=\"Next\">\r\n"+
								" <span aria-hidden=\"true\">&raquo;</span></a>";
		}else{
			strList = strList + " <li class=\"page-item\">\r\n" + 
					"		      <a class=\"page-link\" href=\"#\" aria-label=\"Next\">\r\n" + 
					"		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + 
					"		      </a>\r\n" + 
					"		    </li>";
		}//end else

		return strList;
		
	}//indexList
	
	public List<QnaBoardDomain> searchQnaList(SearchQnaVO sqVO){
		List<QnaBoardDomain> qList = null;
		
		QnaDAO qDao = QnaDAO.getInstance();
		
		try {
			qList = qDao.selectQnaList(sqVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return qList;
		
	}//searchQnaList

	
	public QnaDetailDomain searchQnaDetail(String code) {
		QnaDetailDomain qdd=null;
		QnaDAO qDAO=QnaDAO.getInstance();
		qdd=qDAO.selectQnaDetail(code);
		return qdd;
	}
	
	public void updateQnaAnswer(QnaDetailVO qdVO) {
		QnaDAO qDAO=QnaDAO.getInstance();
		qDAO.updateQnaAnswer(qdVO);
	}
	
	public void deleteQna(String code) {
		QnaDAO qDAO=QnaDAO.getInstance();
		qDAO.deleteQna(code);
	}
	
	

}
