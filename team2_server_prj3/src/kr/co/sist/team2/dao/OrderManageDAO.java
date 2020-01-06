package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.OrderAllListDomain;
import kr.co.sist.team2.domain.OrderCalculateTabDomain;
import kr.co.sist.team2.vo.OrderSearchCommVO;

public class OrderManageDAO {

	private static OrderManageDAO omDao;
	private static SqlSessionFactory ssf;

	private OrderManageDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public static OrderManageDAO getInstance() {
		if (omDao == null) {
			omDao = new OrderManageDAO();
		} // end if
		return omDao;
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

	public List<OrderAllListDomain> selectAllOrderData(OrderSearchCommVO odcVO) throws SQLException {
		List<OrderAllListDomain> orderDatas = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			orderDatas = ss.selectList("kr.co.sist.team2.dao.mapper.OrderMapper.selectAllOrder", odcVO);
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return orderDatas;
	}// selectAllOrderData

	public int updateOrderStatus(String oCode) throws SQLException {
		int flagCnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			flagCnt = ss.update("kr.co.sist.team2.dao.mapper.OrderMapper.orderStatusUpdate", oCode);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flagCnt;
	}// selectAllOrderData

	public List<OrderCalculateTabDomain> selectOrderTab(OrderSearchCommVO odcVO) throws SQLException {
		List<OrderCalculateTabDomain> searchData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			searchData = ss.selectList("kr.co.sist.team2.dao.mapper.OrderMapper.selectOrderTab", odcVO);
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchData;
	}// selectOrderTab

	public int selectOrderMoney(OrderSearchCommVO odcVO) throws SQLException {
		int money = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			if(ss.selectOne("kr.co.sist.team2.dao.mapper.OrderMapper.selectCalculate", odcVO)!=null) {
				money = ss.selectOne("kr.co.sist.team2.dao.mapper.OrderMapper.selectCalculate", odcVO);
			}
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return money;
	}// selectOrderMoney

}// class
