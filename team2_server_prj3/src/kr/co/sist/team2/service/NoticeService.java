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
	 * �˻� ���� �޾Ƽ� �˻� ���� ���ٸ� ��ü���� ������ ��ȸ�ϰ�, �˻� ���� �ִٸ� �˻� ���� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��.
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
	 * �� ȭ�鿡 ������ �Խù��� ��
	 * 
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		return pageScale;
	}// pageScale

	/**
	 * �� �������� �����ֱ� ���� �ʿ��� ������ ��
	 * 
	 * @param pageScale
	 * @param totalCount
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;

		// pageScale�� �� �������� ������ ������ �Խù��� ������
		if (totalCount % pageScale != 0) {
			totalPage++;
		} // end if

		return totalPage;
	}// totalPage

	/**
	 * �������� ���� ��ȣ�� ���ϴ� ��
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
	 * �������� �� ��ȣ�� ���ϴ� ��
	 * 
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;

		return endNum;
	}// endNum

	//////////////////////////////////////// �����Խ���//////////////////////////////////////////////////

	/**
	 * �˻� ����, ���� ��ȣ, �� ��ȣ�� �ش��ϴ� �����Խ��� ����Ʈ ��ȸ
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
	 * �Խ��� �۾���
	 * 
	 * @param nwVO
	 * @return
	 */
	public boolean addNoti(NotiWriteVO nwVO) {
		boolean flag = false;

		MemberDAO mDao = MemberDAO.getInstance();
		try {
			int cnt = mDao.insertNoti(nwVO);// ��� 1�� cnt�� �־���
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
	
	

	//////////////////////////////// ������ �ε���/////////////////////////////////////////////

	public String indexList(IndexListVO ilVO) {
		int pagenumber; // ȭ�鿡 ������ ������ �ε��� ��
		int startpage; // ȭ�鿡 ������ ���������� ��ȣ
		int endpage; // ȭ�鿡 ������ ������������ ��ȣ
		int curpage; // �̵��ϰ��� �ϴ� ������ ��ȣ

		String strList = ""; // ���ϵ� ������ �ε��� ����Ʈ

		pagenumber = 10; // �� ȭ���� ������ �ε��� ��

		// ���� ��������ȣ ���ϱ�
		startpage = ((ilVO.getCurrentPage() - 1) / pagenumber) * pagenumber + 1;

		// ������ ��������ȣ ���ϱ�
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;

		// �� ������ ���� ���� ������������ ��ȣ���� �������

		// �� ������ ���� ������������ ��ȣ�� ��

		if (ilVO.getTotalPage() <= endpage) {
			endpage = ilVO.getTotalPage();
		} // end if

		// ù��° ������ �ε��� ȭ���� �ƴѰ��
		if (ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // ���������� ��ȣ���� 1 ���� �������� �̵�
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

		// ���������� ��ȣ���� ������������ ��ȣ���� ȭ�鿡 ǥ��
		curpage = startpage;

		while (curpage <= endpage) {
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='����������'>"
						+ ilVO.getCurrentPage() + "</a></li>";
			} else {
				strList = strList + "<li class=\"page-item\"><a class=\"page-link\" href=" + ilVO.getUrl()
						+ "?current_page=" + curpage + ">" + curpage + "</a></li>";
			} // end else

			curpage++;
		} // end while

//		strList = strList + " ... ";

		// �ڿ� �������� �� �ִ°��
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
