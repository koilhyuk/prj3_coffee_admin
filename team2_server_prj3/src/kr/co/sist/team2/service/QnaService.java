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
	 * �˻� ���� ���� ��ü���� ���� ��ȸ
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
	 * Qna����Ʈ �� �������� ������ Qna�� ����
	 * @return
	 */
	public int pageScale() {
		int pageScale = 10;
		
		return pageScale;		
	}//pageScale
	
	/**
	 * �� ������ ����
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
	 * �������� ���� ��ȣ
	 * @param pageScale
	 * @param currentPage
	 * @return
	 */
	public int StartNum(int pageScale, int currentPage) {
		int startNum = currentPage * pageScale - pageScale +1;		
		
		return startNum;
	}//StartNum
	
	/**
	 * �������� �� ��ȣ�� ���ϴ� ��
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;
		
		return endNum;
	}//endNum
	
	
	public String indexList(IndexListVO ilVO) {
		int pagenumber; // ȭ�鿡 ������ ������ �ε��� ��
		int startpage; // ȭ�鿡 ������ ���������� ��ȣ
		int endpage; // ȭ�鿡 ������ ������������ ��ȣ
		int curpage; // �̵��ϰ��� �ϴ� ������ ��ȣ

		String strList=""; // ���ϵ� ������ �ε��� ����Ʈ

		pagenumber = 10; // �� ȭ���� ������ �ε��� �� 

		// ���� ��������ȣ ���ϱ�
		startpage = ((ilVO.getCurrentPage()- 1) / pagenumber) * pagenumber + 1;

		// ������ ��������ȣ ���ϱ�
		endpage = (((startpage - 1) + pagenumber) / pagenumber) * pagenumber;

		// �� ������ ���� ���� ������������ ��ȣ���� ������� 

		// �� ������ ���� ������������ ��ȣ�� ��


		if (ilVO.getTotalPage()<= endpage){
			endpage = ilVO.getTotalPage();
		}//end if

		// ù��° ������ �ε��� ȭ���� �ƴѰ��
		if ( ilVO.getCurrentPage() > pagenumber) {
			curpage = startpage - 1; // ���������� ��ȣ���� 1 ���� �������� �̵�
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

		// ���������� ��ȣ���� ������������ ��ȣ���� ȭ�鿡 ǥ��
		curpage = startpage;

		while (curpage <= endpage){
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='����������'>"+ilVO.getCurrentPage()+"</a></li>";
			} else {
				strList = strList +"<li class=\"page-item\"><a class=\"page-link\" href="+ilVO.getUrl()+"?current_page="+curpage+">"+curpage+"</a></li>";
			}//end else

			curpage++;
		}//end while

//		strList = strList + " ... ";

		// �ڿ� �������� �� �ִ°��
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
