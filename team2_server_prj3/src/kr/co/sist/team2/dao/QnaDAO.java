package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.QnaBoardDomain;
import kr.co.sist.team2.domain.QnaDetailDomain;
import kr.co.sist.team2.domain.QnaListDomain;
import kr.co.sist.team2.vo.QnaDetailVO;
import kr.co.sist.team2.vo.QnaRangeVO;
import kr.co.sist.team2.vo.SearchQnaVO;

public class QnaDAO {
	
	private static QnaDAO qDao;
	private static SqlSessionFactory ssf;

	public QnaDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();	
	}
	
	public static QnaDAO getInstance() {
		if(qDao == null) {
			qDao = new QnaDAO();
		}//end if		
		return qDao;
	}//getInstance
	
	public SqlSessionFactory getSessionFactory() throws IOException{
		if(ssf == null) {
			Reader reader = null;
			try {
				// 1. ������ xml�� ��Ʈ������ ����
				reader = Resources.getResourceAsReader("kr/co/sist/team2/dao/mybatis-config.xml");
				// 2. SqlSessionFactoryBuilder ����
				SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				// 3. SqlSessionFactory ���
				ssf = ssfb.build(reader);

			} finally {
				if (reader != null) {reader.close();} // end if

			} // end finally
		} // end if
		return ssf;
	}// getSessionFactory
			
	
	/**
	 * �˻���� ��� Qna �� ����
	 * @param sqVO
	 * @return
	 */
	public int selectTotalCount(SearchQnaVO sqVO) throws SQLException{		
		int cnt = 0;
		try {
		//MyBatis Handler ���
		SqlSession ss = getSessionFactory().openSession();
		//�������� �� ��� ���
			cnt = ss.selectOne("kr.co.sist.team2.dao.mapper.qnaMapper.selectTotalCount", sqVO);
		//Handler ����
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return cnt;		
	}//selectTotalCount
	
	/**
	 * Qna�� ��ȸ
	 * @param sqVO
	 * @param qrVO
	 * @return
	 * @throws SQLException
	 */
	public List<QnaBoardDomain> selectQnaList(SearchQnaVO sqVO) throws SQLException{
		
		List<QnaBoardDomain> qList = null;
		
		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
			qList = ss.selectList("kr.co.sist.team2.dao.mapper.qnaMapper.qnaBoardList", sqVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch		
		
		return qList;
	}//selectQnaList
	
	
	public QnaDetailDomain selectQnaDetail(String code) {
		QnaDetailDomain qdd=null;
		
		try {
			SqlSession ss=getSessionFactory().openSession();
			qdd=ss.selectOne("kr.co.sist.team2.dao.mapper.qnaMapper.selectQnaAdmin",code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return qdd;
	}
	
	public void updateQnaAnswer(QnaDetailVO qdVO) {
		try {
			SqlSession ss=getSessionFactory().openSession();
			int flag=ss.update("kr.co.sist.team2.dao.mapper.qnaMapper.updateAnswer", qdVO);
			if(flag==1) {
				ss.commit();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteQna(String code) {
		int flag=0;
		try {
			SqlSession ss=getSessionFactory().openSession();
			flag=ss.delete("kr.co.sist.team2.dao.mapper.qnaMapper.deleteQnaAdmin", code);
			if(flag==1) {
				ss.commit();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}//class
