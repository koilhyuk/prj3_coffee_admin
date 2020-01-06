package kr.co.sist.team2.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.team2.dao.MemberDAO;
import kr.co.sist.team2.domain.NoticeDetailDomain;
import kr.co.sist.team2.domain.NoticeListDomain;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.NotiUpdateVO;
import kr.co.sist.team2.vo.NotiWriteVO;
import kr.co.sist.team2.vo.SearchVO;

public class NoticeService {

	/**
	 * 검색 값을 받아서 검색 값이 없다면 전체글의 개수를 조회하고, 검색 값이 있다면 검색 값에 해당하는 글의 개수를 조회하는 일.
	 * 
	 * @param sVO
	 * @return
	 */
	public int totalCount(SearchVO sVO) {
		int totalCount = 0;
		MemberDAO mDao = MemberDAO.getInstance();
		try {
			totalCount = mDao.selectNotiCount(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return totalCount;
	}// totalCount

	/**
	 * 한 화면에 보여줄 게시물의 수
	 * 
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		return pageScale;
	}// pageScale

	/**
	 * 총 페이지를 보여주기 위해 필요한 페이지 수
	 * 
	 * @param pageScale
	 * @param totalCount
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;

		// pageScale로 딱 떨어지지 않으면 나머지 게시물을 보여줌
		if (totalCount % pageScale != 0) {
			totalPage++;
		} // end if

		return totalPage;
	}// totalPage

	/**
	 * 페이지의 시작 번호를 구하는 일
	 * 
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int startNum(int pageScale, int currentPage) {
		int startNum = currentPage * pageScale - pageScale + 1;

		return startNum;
	}// startNum

	/**
	 * 페이지의 끝 번호를 구하는 일
	 * 
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;

		return endNum;
	}// endNum

	//////////////////////////////////////// 공지게시판//////////////////////////////////////////////////

	/**
	 * 검색 값과, 시작 번호, 끝 번호에 해당하는 공지게시판 리스트 조회
	 * 
	 * @param sVO
	 * @return
	 */
	public List<NoticeListDomain> searchNoticeList(SearchVO sVO) {
		List<NoticeListDomain> notiList = null;

		MemberDAO mDao = MemberDAO.getInstance();
		try {
			notiList = mDao.searchNotiList(sVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return notiList;
	}// searchNoticeList

	/**
	 * 게시판 글쓰기
	 * 
	 * @param nwVO
	 * @return
	 */
	public boolean addNoti(NotiWriteVO nwVO) {
		boolean flag = false;

		MemberDAO mDao = MemberDAO.getInstance();
		try {
			int cnt = mDao.insertNoti(nwVO);// 결과 1을 cnt에 넣어줌
			if (cnt == 1) {
				flag = true;
			} // end if
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return flag;
	}// addNoti

	public NoticeDetailDomain searchNotiDetail(String nCode) {
		NoticeDetailDomain ndd = null;

		MemberDAO mDao = MemberDAO.getInstance();
		try {
			ndd = mDao.searchNotiDetail(nCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return ndd;
	}// searchNotiDetail

	public boolean modifyNoti(NotiUpdateVO nuVO) {
		boolean flag = false;
		MemberDAO mDao = MemberDAO.getInstance();
		try {
			flag = mDao.updateNotice(nuVO) > 0;
//			if(flag) {
//				
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}// modifyNoti
	
	public boolean removeNotice(String noticeCode) {
		boolean flag = false;
		
		MemberDAO mDao = MemberDAO.getInstance();
		try {
			flag = mDao.deleteNotice(noticeCode) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
		return flag;
	}//removeNotice
	
	

	//////////////////////////////// 페이지 인덱스/////////////////////////////////////////////

	public String indexList(IndexListVO ilVO) {
		int pagenumber; // 화면에 보여질 페이지 인덱스 수
		int startpage; // 화면에 보여질 시작페이지 번호
		int endpage; // 화면에 보여질 마지막페이지 번호
		int curpage; // 이동하고자 하는 페이지 번호

		String strList = ""; // 리턴될 페이지 인덱스 리스트

		pagenumber = 10; // 한 화면의 페이지 인덱스 수

		// 시작 페이지번호 구하기
		startpage = ((ilVO.getCurrentPage() - 1) / pagenumber) * pagenumber + 1;

		// 마지막 페이지번호 구하기
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;

		// 총 페이지 수가 계산된 마지막페이지 번호보다 작을경우

		// 총 페이지 수가 마지막페이지 번호가 됨

		if (ilVO.getTotalPage() <= endpage) {
			endpage = ilVO.getTotalPage();
		} // end if

		// 첫번째 페이지 인덱스 화면이 아닌경우
		if (ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // 시작페이지 번호보다 1 적은 페이지로 이동
			strList = strList + "<li class=\"page-item\"><a class=\"page-link\" href=" + ilVO.getUrl()
					+ "?current_page=" + curpage + " aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span></a></li>";
		} else {
			strList = strList + "<li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li>";
		}

//		strList = strList + " ... ";

		// 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
		curpage = startpage;

		while (curpage <= endpage) {
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='현재페이지'>"
						+ ilVO.getCurrentPage() + "</a></li>";
			} else {
				strList = strList + "<li class=\"page-item\"><a class=\"page-link\" href=" + ilVO.getUrl()
						+ "?current_page=" + curpage + ">" + curpage + "</a></li>";
			} // end else

			curpage++;
		} // end while

//		strList = strList + " ... ";

		// 뒤에 페이지가 더 있는경우
		if (ilVO.getTotalPage() > endpage) {
			curpage = endpage + 1;
			strList = strList + "<li class=\"page-item\">\r\n" + "		     <a class=\"page-link\" href="
					+ ilVO.getUrl() + "?current_page=" + curpage + " aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span></a>";
		} else {
			strList = strList + " <li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li>";
		} // end else

		return strList;
	}// indexList

}// class
