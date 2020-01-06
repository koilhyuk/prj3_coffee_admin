package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.MemberDetailDomain;
import kr.co.sist.team2.domain.MemberListDomain;
import kr.co.sist.team2.domain.MemberOrderListDomain;
import kr.co.sist.team2.domain.NoticeDetailDomain;
import kr.co.sist.team2.domain.NoticeListDomain;
import kr.co.sist.team2.vo.NotiUpdateVO;
import kr.co.sist.team2.vo.NotiWriteVO;
import kr.co.sist.team2.vo.PointListiVO;
import kr.co.sist.team2.vo.SearchVO;

public class MemberDAO {

	private static MemberDAO mDao;
	private static SqlSessionFactory ssf;

	private MemberDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public static MemberDAO getInstance() {
		if (mDao == null) {
			mDao = new MemberDAO();
		} // end if
		return mDao;
	}// getInstance

	public SqlSessionFactory getSessionFactory() throws IOException {
		if (ssf == null) {
			Reader reader = null;
			try {
				// 1. 설정용 xml을 스트림으로 연결
				reader = Resources.getResourceAsReader("kr/co/sist/team2/dao/mybatis-config.xml");
				// 2. SqlSessionFactoryBuilder 생성
				SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
				// 3. SqlSessionFactory 얻기
				ssf = ssfb.build(reader);

			} finally {
				if (reader != null) {
					reader.close();
				} // end if

			} // end finally
		} // end if
		return ssf;
	}// getSessionFactory

	///////////////////////////////////////// 회원게시판/////////////////////////////////////////////////
	/**
	 * 회원 게시판의 모든 리스트 개수
	 * 
	 * @param sVO
	 * @return
	 * @throws SQLException
	 */
	public int selectTotalCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
			cnt = ss.selectOne("kr.co.sist.team2.dao.mapper.MemberMapper.selectTotalCount", sVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return cnt;
	}// selectTotalCount

	/**
	 * 회원 게시판 메인
	 * 
	 * @param sVO
	 * @return
	 * @throws SQLException
	 */
	public List<MemberListDomain> selectMemAllSearch(SearchVO sVO) throws SQLException {
		List<MemberListDomain> mAllList = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			mAllList = ss.selectList("kr.co.sist.team2.dao.mapper.MemberMapper.memberAllList", sVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return mAllList;
	}// selectMemSelectSearch

	/**
	 * 회원 게시판 디테일 : 회원 정보, 활동 정보
	 * 
	 * @param mId
	 * @return
	 * @throws SQLException
	 */
	public MemberDetailDomain searchMemDetail(String mId) throws SQLException {
		MemberDetailDomain mdd = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			mdd = ss.selectOne("kr.co.sist.team2.dao.mapper.MemberMapper.memberInfo", mId);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return mdd;
	}// searchMemDetail

	/**
	 * 회원 게시판 디테일 : 주문 내역
	 * 
	 * @param mId
	 * @return
	 * @throws SQLException
	 */
	public List<MemberOrderListDomain> searchMemOrderList(String mId) throws SQLException {
		List<MemberOrderListDomain> mdld = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			mdld = ss.selectList("kr.co.sist.team2.dao.mapper.MemberMapper.memberOrder", mId);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return mdld;
	}// searchMemOrderList

	/**
	 * 회원 게시판 디테일 : 포인트
	 * 
	 * @param mId
	 * @return
	 * @throws SQLException
	 */
	public List<PointListiVO> searchPointList(String mId) throws SQLException {
		List<PointListiVO> plVo = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			plVo = ss.selectList("kr.co.sist.team2.dao.mapper.MemberMapper.memberPoint", mId);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return plVo;
	}// searchPointList
	
	
	public int countMPoint(String mId) throws SQLException{
		int mPointCount = 0;
		
		try {
			SqlSession ss = getSessionFactory().openSession();
			mPointCount = ss.selectOne("kr.co.sist.team2.dao.mapper.MemberMapper.memberPointCount", mId);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//end catch
		
		return mPointCount;
		
	}//memberPointCount
	

	public int deleteMember(String mId) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.delete("kr.co.sist.team2.dao.mapper.MemberMapper.deleteMember", mId);
			if (cnt > 0) {
				ss.commit();
			} // end if
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return cnt;

	}// deleteMember

/////////////////////////////////////////공지 게시판///////////////////////////////////////////////////////

	/**
	 * 공지 게시판의 모든 리스트 개수
	 * 
	 * @param sVO
	 * @return
	 * @throws SQLException
	 */
	public int selectNotiCount(SearchVO sVO) throws SQLException {
		int cnt = 0;
		SqlSession ss;

		try {
			ss = getSessionFactory().openSession();
			cnt = ss.selectOne("kr.co.sist.team2.dao.mapper.NoticeMapper.selectNotiCount", sVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return cnt;
	}// selectNotiCount

	/**
	 * 공지게시판 메인
	 * 
	 * @param sVO
	 * @return
	 * @throws SQLException
	 */
	public List<NoticeListDomain> searchNotiList(SearchVO sVO) throws SQLException {
		List<NoticeListDomain> noticeList = null;

		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
			noticeList = ss.selectList("kr.co.sist.team2.dao.mapper.NoticeMapper.notiList", sVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return noticeList;
	}// searchMemNotiList

	public int insertNoti(NotiWriteVO nwVO) throws SQLException {
		int cnt = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.insert("kr.co.sist.team2.dao.mapper.NoticeMapper.notiInsert", nwVO);// cnt = 1
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return cnt;
	}// insertNoti

	public NoticeDetailDomain searchNotiDetail(String nCode) throws SQLException {
		NoticeDetailDomain nld = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			nld = ss.selectOne("kr.co.sist.team2.dao.mapper.NoticeMapper.noticeProcessData", nCode);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return nld;
	}// searchNotiDetail

	public int updateNotice(NotiUpdateVO nuVO) throws SQLException {
		int cnt = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.update("kr.co.sist.team2.dao.mapper.NoticeMapper.updateNoticeData", nuVO);
			if (cnt > 0) {
				ss.commit();
			} // end if
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		return cnt;
	}// updateNotice
	
	public int deleteNotice(String noticeCode) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.delete("kr.co.sist.team2.dao.mapper.NoticeMapper.deleteNoticeData", noticeCode);
			if (cnt > 0) {
				ss.commit();
			} // end if
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return cnt;

	}// deleteMember
	
}// class
