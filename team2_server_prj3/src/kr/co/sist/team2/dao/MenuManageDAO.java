package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.MenuAllDomain;
import kr.co.sist.team2.domain.MenuCateDomain;
import kr.co.sist.team2.domain.MenuCateSearchDomain;
import kr.co.sist.team2.domain.MenuOriginInformDomain;
import kr.co.sist.team2.domain.MenuOriginRecipeDomain;
import kr.co.sist.team2.vo.MenuNewAddVO;
import kr.co.sist.team2.vo.MenuNewRecipeVO;
import kr.co.sist.team2.vo.MenuOriginModifyVO;
import kr.co.sist.team2.vo.MenuSearchVO;

public class MenuManageDAO {

	private static MenuManageDAO mmDao;
	private static SqlSessionFactory ssf;

	private MenuManageDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}

	public static MenuManageDAO getInstance() {
		if (mmDao == null) {
			mmDao = new MenuManageDAO();
		} // end if
		return mmDao;
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

	public List<MenuCateDomain> selectMenuCate() throws SQLException {
		List<MenuCateDomain> cateData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cateData = ss.selectList("kr.co.sist.team2.dao.mapper.MenuMapper.selectMenuCate");
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		return cateData;
	}// selectMenuCate

	public List<MenuAllDomain> selectAllLoading(MenuSearchVO msVO) throws SQLException {
		List<MenuAllDomain> menuData = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			menuData = ss.selectList("kr.co.sist.team2.dao.mapper.MenuMapper.selectAllMenu", msVO);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return menuData;
	}// selectAllMenu

	public int selectTotalCount(MenuSearchVO msVO) throws SQLException {
		int cnt = 0;

		try {
			// MyBatis Handler 얻기
			SqlSession ss = getSessionFactory().openSession();

			// 쿼리 수행 후 결과 받기
			cnt = ss.selectOne("kr.co.sist.team2.dao.mapper.MenuMapper.selectTotalCount", msVO);
			// MyBatis Handler 끊기
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		} // end catch
		return cnt;
	}// selectTotalCount

	public List<MenuAllDomain> selectAllMenu(MenuSearchVO msVO) throws SQLException {
		List<MenuAllDomain> menuData = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			menuData = ss.selectList("kr.co.sist.team2.dao.mapper.MenuMapper.selectAllMenu", msVO);
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return menuData;
	}// selectAllMenu

	public List<MenuCateSearchDomain> selectAllCateType() throws SQLException {
		List<MenuCateSearchDomain> cateData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cateData = ss.selectList("kr.co.sist.team2.dao.mapper.MenuMapper.selectAllCateType");

			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cateData;
	}// selectAllCateType

	public int insertNewCate(String gcName) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.insert("kr.co.sist.team2.dao.mapper.MenuMapper.insertNewCate", gcName);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return cnt;
	}// insertNewCate

	public List<MenuCateSearchDomain> selectModalCate(String selectMenu) throws SQLException {
		List<MenuCateSearchDomain> cateData = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cateData = ss.selectList("kr.co.sist.team2.dao.mapper.MenuMapper.selectModalCate", selectMenu);
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cateData;
	}// selectModalCate

	public int insertNewMenuInform(MenuNewAddVO mvaVO) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.insert("kr.co.sist.team2.dao.mapper.MenuMapper.insertNewMenu", mvaVO);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}// insertNewCate

	public int insertNewRecipeInform(MenuNewRecipeVO mnrVO) throws SQLException {
		int cnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.insert("kr.co.sist.team2.dao.mapper.MenuMapper.insertNewRecipe", mnrVO);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}// insertNewCate

	public MenuOriginInformDomain selectOriginMenu(String gdName) throws SQLException {
		MenuOriginInformDomain moid = null;

		try {
			SqlSession ss = getSessionFactory().openSession();
			moid = ss.selectOne("kr.co.sist.team2.dao.mapper.MenuMapper.selectOriginMenuInform", gdName);
			moid.setGdName(gdName);
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return moid;
	}// selectOriginMenu

	public MenuOriginRecipeDomain selectOriginRecipe(String gdName) throws SQLException {
		MenuOriginRecipeDomain mord = null;
		try {
			SqlSession ss = getSessionFactory().openSession();
			mord = ss.selectOne("kr.co.sist.team2.dao.mapper.MenuMapper.selectOriginMenuRecipe", gdName);
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return mord;
	}// selectOriginMenu

	public int deleteOriginGoods(String DeleteGdName) throws SQLException {
		int cnt = 0;
		try {
			SqlSession ss = getSessionFactory().openSession();
			cnt = ss.update("kr.co.sist.team2.dao.mapper.MenuMapper.deleteGoods", DeleteGdName);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}// deleteOriginGoods

	public boolean updateOriginGoods(MenuOriginModifyVO momVO) throws SQLException {
		boolean updateFlag = false;
		try {
			SqlSession ss = getSessionFactory().openSession();
			updateFlag = (ss.update("kr.co.sist.team2.dao.mapper.MenuMapper.updateOriginGoods", momVO) > 0);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return updateFlag;
	}// deleteOriginGoods

	public boolean updateOriginRecipe(MenuOriginModifyVO momVO) throws SQLException {
		boolean updateFlag = false;

		try {
			SqlSession ss = getSessionFactory().openSession();
			updateFlag = (ss.update("kr.co.sist.team2.dao.mapper.MenuMapper.updateOriginRecipe", momVO) > 0);
			ss.commit();
			ss.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return updateFlag;
	}// deleteOriginGoods

	public boolean selectGoodsDupli(MenuOriginModifyVO momVO) throws SQLException {
		boolean chkFlag = false;// 중복 아님
		int chkCnt = 0;

		try {
			SqlSession ss = getSessionFactory().openSession();
			chkCnt = ss.selectOne("kr.co.sist.team2.dao.mapper.MenuMapper.selectDuplication", momVO);
			if (chkCnt > 0) {
				chkFlag = true;// 중복 됨
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return chkFlag;
	}// selectGoodsDupli

	
	public boolean insertAddModifyRecipe(MenuOriginModifyVO momVO)throws SQLException{
		boolean insertFlag = false;
		try {
			SqlSession ss = getSessionFactory().openSession();
			insertFlag = (ss.insert("kr.co.sist.team2.dao.mapper.MenuMapper.insertNewModifyRecipe", momVO) > 0);
			ss.commit();
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return insertFlag;
	}//insertAddModifyRecipe
	
	
	
	
}// class
