package kr.co.sist.team2.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.sist.team2.dao.CoffeMainDAO;
import kr.co.sist.team2.domain.HomeNoticeDetailDomain;
import kr.co.sist.team2.domain.HomeNoticeDomain;
import kr.co.sist.team2.domain.HomeOrderCntVO;
import kr.co.sist.team2.domain.HomeQnaDetailDomain;
import kr.co.sist.team2.domain.HomeQnaDomain;
import kr.co.sist.team2.domain.HomeSellDomain;
import kr.co.sist.team2.vo.HomeNoticeModifyVO;
import kr.co.sist.team2.vo.HomeNoticeNewInsertVO;
import kr.co.sist.team2.vo.HomeQnaCommVO;
import kr.co.sist.team2.vo.HomeSellFlagVO;

public class MainService {

	public List<HomeQnaDomain> searchHomeQna() {
		List<HomeQnaDomain> homeQnaData = null;

		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		try {
			homeQnaData = cmDao.selectHomeQna();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return homeQnaData;
	}// searchHomeQna

	public HomeSellDomain searchHomeSell() {
		HomeSellDomain hsd = null;
		HomeSellFlagVO hsfVO = new HomeSellFlagVO();
		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();

		try {
			hsd = new HomeSellDomain();
			for (int i = 0; i < 2; i++) {
				hsfVO.setSellFlag(String.valueOf(i));
				if (i == 0) {
					hsd.setDaySell(cmDao.selectHomeSell(hsfVO));
				} else {
					hsd.setMonthSell(cmDao.selectHomeSell(hsfVO));
				} // end if
			} // end for
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return hsd;
	}// searchHomeSell

	public List<HomeNoticeDomain> searchHomeNotice() {
		List<HomeNoticeDomain> homeNoticeData = null;

		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		try {
			homeNoticeData = cmDao.selectHomeNotice();

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return homeNoticeData;
	}// searchHomeNotice

	public HomeQnaDetailDomain searchHomeQnaDet(String qCode) {
		HomeQnaDetailDomain hqdd = null;

		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		try {
			hqdd = cmDao.selectHomeQnaDet(qCode);

		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch

		return hqdd;
	}// searchHomeNotice

	public int HomeQnaComm(HomeQnaCommVO hqcVO) {
		int flag = 0;

		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();

		try {
			flag = cmDao.HomeQnaUpdate(hqcVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}//HomeQnaComm
	
	
	
	public HomeNoticeDetailDomain searchHomeNoticeDet(String nCode) {
		HomeNoticeDetailDomain hndd = null;
		
		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		try {
			hndd = cmDao.selectNoticeDetail(nCode);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		
		return hndd;
	}// searchHomeNotice

	public int HomeNoticeModify(HomeNoticeModifyVO hnmVO) {
		int cnt =0;
		
		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		
		try {
			cnt=	cmDao.HomeNoticeUpdate(hnmVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//HomeNoticeUpdate
	
	public int HomeNoticeInsert(HomeNoticeNewInsertVO hnniVO) {
		int cnt =0;
		
		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		
		try {
			cnt=	cmDao.HomeNoticeNewInsert(hnniVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}//HomeNoticeInsert
	
	public HomeOrderCntVO searchHomeOrderCnt() {
		HomeOrderCntVO hocVO = new HomeOrderCntVO();
		
		CoffeMainDAO cmDao = CoffeMainDAO.getInstance();
		try {
		
		for (int i = 0; i < 2; i++) {
			if (i == 0) {
				hocVO.setPreCnt(cmDao.selectHomeOrderCnt(String.valueOf(i)));
			} else {
				hocVO.setComCnt(cmDao.selectHomeOrderCnt(String.valueOf(i)));
			} // end else
		} // end for
		
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hocVO;
	}//selectHomeOrderCnt
	
	
	
	
	

}// class
