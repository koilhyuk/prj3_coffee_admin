package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.HomeNoticeDetailDomain;
import kr.co.sist.team2.domain.HomeNoticeDomain;
import kr.co.sist.team2.domain.HomeQnaDetailDomain;
import kr.co.sist.team2.domain.HomeQnaDomain;
import kr.co.sist.team2.vo.HomeNoticeModifyVO;
import kr.co.sist.team2.vo.HomeNoticeNewInsertVO;
import kr.co.sist.team2.vo.HomeQnaCommVO;
import kr.co.sist.team2.vo.HomeSellFlagVO;

public class CoffeMainDAO {

	private static CoffeMainDAO cmDao;
	private static SqlSessionFactory ssf;

	private CoffeMainDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public static CoffeMainDAO getInstance() {
		if (cmDao == null) {
			cmDao = new CoffeMainDAO();
		} // end if
		return cmDao;
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

	public List<HomeQnaDomain> selectHomeQna() throws SQLException {
		List<HomeQnaDomain> homeQnaData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			homeQnaData = ss.selectList("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeQna");
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return homeQnaData;
	}// selectHomeQna

	public int selectHomeSell(HomeSellFlagVO hsfVO) throws SQLException {
		int sellCnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			sellCnt = ss.selectOne("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeSell", hsfVO);

			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return sellCnt;
	}// selectHomeSell

	public List<HomeNoticeDomain> selectHomeNotice() throws SQLException {
		List<HomeNoticeDomain> homeNoticeData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			homeNoticeData = ss.selectList("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeNotice");
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return homeNoticeData;
	}// selectHomeNotice

	public HomeQnaDetailDomain selectHomeQnaDet(String qCode) throws SQLException {
		HomeQnaDetailDomain hqdd = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			hqdd = ss.selectOne("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeQnaDetail", qCode);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch

		return hqdd;
	}// selectHomeQnaDet

	public int HomeQnaUpdate(HomeQnaCommVO hqcVO) throws SQLException {
		int flag = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			flag = ss.update("kr.co.sist.team2.dao.mapper.HomeMapper.insertHomeQnaComm", hqcVO);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return flag;
	}// HomeQnaUpdate

	public HomeNoticeDetailDomain selectNoticeDetail(String nCode) throws SQLException {
		HomeNoticeDetailDomain hndd = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			hndd = ss.selectOne("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeNotiModify", nCode);

			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return hndd;
	}// selectNoticeDetail

	public int HomeNoticeUpdate(HomeNoticeModifyVO hnmVO) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.update("kr.co.sist.team2.dao.mapper.HomeMapper.updateHomeNotice", hnmVO);
			ss.commit();

			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}// HomeNoticeUpdate

	public int HomeNoticeNewInsert(HomeNoticeNewInsertVO hnniVO) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.insert("kr.co.sist.team2.dao.mapper.HomeMapper.insertNewNotice", hnniVO);
			ss.commit();

			ss.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cnt;
	}// HomeNoticeNewInsert

	public int selectHomeOrderCnt(String Status) throws SQLException {
		int statusCnt = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			statusCnt = ss.selectOne("kr.co.sist.team2.dao.mapper.HomeMapper.selectHomeOrderCnt", Status);

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return statusCnt;
	}// selectHomeOrderCnt

}// class