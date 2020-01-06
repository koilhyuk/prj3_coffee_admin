package kr.co.sist.team2.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import kr.co.sist.team2.dao.MenuManageDAO;
import kr.co.sist.team2.domain.MenuAllDomain;
import kr.co.sist.team2.domain.MenuCateDomain;
import kr.co.sist.team2.domain.MenuCateSearchDomain;
import kr.co.sist.team2.domain.MenuOriginInformDomain;
import kr.co.sist.team2.domain.MenuOriginRecipeDomain;
import kr.co.sist.team2.vo.IndexListVO;
import kr.co.sist.team2.vo.MenuNewAddVO;
import kr.co.sist.team2.vo.MenuNewRecipeVO;
import kr.co.sist.team2.vo.MenuOriginModifyVO;
import kr.co.sist.team2.vo.MenuSearchVO;

public class MenuService {

	public List<MenuCateDomain> searchMenuCate() {
		List<MenuCateDomain> cateData = null;
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			cateData = mmDao.selectMenuCate();
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return cateData;
	}// searchMenuCate

	public List<MenuAllDomain> searchAllLoading(MenuSearchVO msVO) {
		List<MenuAllDomain> menuData = null;
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			menuData = mmDao.selectAllLoading(msVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuData;

	}// searchAllLoading

	public JSONArray searchAllCateType() {
		List<MenuCateSearchDomain> selectCateData = null;
		JSONArray cateData = new JSONArray();
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			JSONObject jo = null;
			selectCateData = mmDao.selectAllCateType();

			MenuCateSearchDomain mcsd = null;
			for (int i = 0; i < selectCateData.size(); i++) {
				mcsd = selectCateData.get(i);
				jo = new JSONObject();
				jo.put("gcCode", mcsd.getGcCode());
				jo.put("gcName", mcsd.getGcName());

				cateData.add(jo);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cateData;
	}// searchAllMenu

	public JSONObject insertAddCate(String gcName) {
		int cnt = 0;
		JSONObject jo = new JSONObject();
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			cnt = mmDao.insertNewCate(gcName);
			jo.put("insertFlag", cnt);

		} catch (SQLException e) {
			e.printStackTrace();
		}// end catch

		return jo;
	}// insertAddCate

	public JSONArray searchModalCate(String selectMenu) {
		List<MenuCateSearchDomain> selectCateData = null;
		JSONArray cateData = new JSONArray();
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			JSONObject jo = null;
			selectCateData = mmDao.selectModalCate(selectMenu);

			MenuCateSearchDomain mcsd = null;
			for (int i = 0; i < selectCateData.size(); i++) {
				mcsd = selectCateData.get(i);
				jo = new JSONObject();
				jo.put("gcCode", mcsd.getGcCode());
				jo.put("gcName", mcsd.getGcName());

				cateData.add(jo);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cateData;
	}// searchModalCate

	public JSONObject insertAddMenu(MenuNewAddVO mvaVO, MultipartFile multipartFile) {
		int cnt = 0;
		JSONObject jo = new JSONObject();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File originFile = null;
		File[] originFileNames = null;
		File file = null;
		File clientfile = null;

		// BookInsertVO의 img가 null이기 때문에 파일명을 가져와 set하여 줍니다.
		mvaVO.setNewMenuImgselect(multipartFile.getOriginalFilename());

		String category = "";
		if ("커피".equals(mvaVO.getModalSelectCate().trim())) {
			category = "coffee";
		} else if ("율리치노".equals(mvaVO.getModalSelectCate().trim())) {
			category = "yuliccino";
		} else if ("티".equals(mvaVO.getModalSelectCate().trim())) {
			category = "tea";
		} else if ("디저트".equals(mvaVO.getModalSelectCate().trim())) {
			category = "dessert";
		}// end else

		try {

			originFile = new File("C:/dev/workspace/team2_server_prj3/WebContent/common/goodsImages/");

			originFileNames = originFile.listFiles();
			for (int i = 0; i < originFileNames.length; i++) {
				if (multipartFile.getOriginalFilename().equals(originFileNames[i].getName())) {
					originFileNames[i].delete();
				} // end if
			} // end for
			file = new File("C:/dev/workspace/team2_server_prj3/WebContent/common/goodsImages/"
					+ multipartFile.getOriginalFilename());
			clientfile = new File("C:/dev/workspace/final_prj/WebContent/common/menuImg/" + category + "/"
					+ multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			fis = new FileInputStream(file.getAbsoluteFile());
			fos = new FileOutputStream(clientfile.getAbsoluteFile());

			int readDate = 0; // 바이트를 저장하는 변수 생성
			while ((readDate = fis.read()) != -1) {
				fos.write(readDate);
			}
			fos.flush();

		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}// end catch
		} // end finally

		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			cnt = mmDao.insertNewMenuInform(mvaVO);
			jo.put("insertFlag", cnt);
			jo.put("gdName", mvaVO.getGdName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}// insertAddCate

	public int insertAddRecipe(MenuNewRecipeVO mnrVO) {
		int cnt = 0;
		JSONObject jo = new JSONObject();
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			cnt = mmDao.insertNewRecipeInform(mnrVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}// end catch
		return cnt;
	}// insertAddCate

	public JSONObject searchOriginMenu(String gdName) {
		MenuOriginInformDomain moid = null;
		MenuOriginRecipeDomain mord = null;
		List<MenuCateSearchDomain> cateDataList = null;
		JSONObject jo = new JSONObject();
		JSONArray cateJsa = new JSONArray();
		JSONObject cateJo = null;
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			moid = mmDao.selectOriginMenu(gdName);
			jo.put("gdName", moid.getGdName());
			jo.put("mName", moid.getmName());
			jo.put("gdImg", moid.getGdImg());
			jo.put("gcName", moid.getGcName());
			jo.put("gdKal", moid.getGdKal());
			jo.put("gdCaffein", moid.getGdCaffein());
			jo.put("gdSugar", moid.getGdSugar());
			jo.put("gdSalt", moid.getGdSalt());
			jo.put("gdInfo", moid.getGdInfo());
			jo.put("gdPrice", moid.getGdPrice());

			MenuCateSearchDomain mcsd = null;
			cateDataList = mmDao.selectModalCate(moid.getmName());
			for (int i = 0; i < cateDataList.size(); i++) {
				mcsd = cateDataList.get(i);
				cateJo = new JSONObject();
				cateJo.put("gcCode", mcsd.getGcCode());
				cateJo.put("gcName", mcsd.getGcName());
				cateJsa.add(cateJo);
			}// end for
			
			jo.put("cateDatas", cateJsa);

			if ("음료".equals(moid.getmName().trim()) && "커피".equals(moid.getGcName().trim())
					|| "율리치노".equals(moid.getGcName().trim())) {
				mord = mmDao.selectOriginRecipe(moid.getGdName());
				if (mord != null) {
					if (mord.getBrMilk() == null || mord.getBrMilk().trim().isEmpty()) {
						mord.setBrMilk("none");
					}// end if
					if (mord.getBrSyrup() == null || mord.getBrSyrup().trim().isEmpty()) {
						mord.setBrSyrup("none");
					}// endif
					if (mord.getBrTopping() == null || mord.getBrTopping().trim().isEmpty()) {
						mord.setBrTopping("none");
					}// endif

					jo.put("brMilk", mord.getBrMilk());
					jo.put("brSyrup", mord.getBrSyrup());
					jo.put("brTopping", mord.getBrTopping());
					jo.put("brType", mord.getBrType());
					jo.put("brShot", mord.getBrShot());
					jo.put("brCream", mord.getBrCream());
				} else {
					jo.put("brMilk", "none");
					jo.put("brSyrup", "none");
					jo.put("brTopping", "none");
					jo.put("brType", "I");
					jo.put("brShot", 0);
					jo.put("brCream", 0);

				}// end else
			} // end if

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jo;
	}// insertAddCate

	public JSONObject deleteGoodsUpdate(String DeleteGdName) {
		boolean deleteFlag = false;
		JSONObject jo = new JSONObject();

		MenuManageDAO mmDao = MenuManageDAO.getInstance();
		try {
			if (mmDao.deleteOriginGoods(DeleteGdName) > 0) {
				deleteFlag = true;
			}
			jo.put("deleteFlag", deleteFlag);

		} catch (SQLException se) {
			se.printStackTrace();
		}// end catch

		return jo;
	}// deleteGoodsUpdate

	public JSONObject originMenuModify(MenuOriginModifyVO momVO, MultipartFile multipartFileModify) {
		boolean totalFlag = false;
		JSONObject joFlag = new JSONObject();
		FileInputStream fis = null;
		FileOutputStream fos = null;
		File originFile = null;
		File[] originFileNames = null;
		File file = null;
		File clientfile = null;

		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		// BookInsertVO의 img가 null이기 때문에 파일명을 가져와 set하여 줍니다.
		momVO.setOriginMenuImg(multipartFileModify.getOriginalFilename());

		String category = "";
		if ("커피".equals(momVO.getOriginMenuCate().trim())) {
			category = "coffee";
		} else if ("율리치노".equals(momVO.getOriginMenuCate().trim())) {
			category = "yuliccino";
		} else if ("티".equals(momVO.getOriginMenuCate().trim())) {
			category = "tea";
		} else if ("디저트".equals(momVO.getOriginMenuCate().trim())) {
			category = "dessert";
		}

		// //2. 경로 설정
		try {
			originFile = new File("C:/dev/workspace/team2_server_prj3/WebContent/common/goodsImages/");

			originFileNames = originFile.listFiles();
			for (int i = 0; i < originFileNames.length; i++) {
				if (multipartFileModify.getOriginalFilename().equals(originFileNames[i].getName())) {
					originFileNames[i].delete();
				} // end if
			} // end for

			file = new File("C:/dev/workspace/team2_server_prj3/WebContent/common/goodsImages/"
					+ multipartFileModify.getOriginalFilename());

			multipartFileModify.transferTo(file);

			clientfile = new File("C:/dev/workspace/final_prj/WebContent/common/menuImg/" + category + "/"
					+ multipartFileModify.getOriginalFilename());

			fis = new FileInputStream(file.getAbsoluteFile());
			fos = new FileOutputStream(clientfile.getAbsoluteFile());

			int readDate = 0; // 바이트를 저장하는 변수 생성
			while ((readDate = fis.read()) != -1) {
				fos.write(readDate);
			}
			fos.flush();

		} catch (IllegalStateException ise) {
			ise.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (fos != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} // end finally

		if ("none".equals(momVO.getOriginMilk())) {
			momVO.setOriginMilk(" ");
		}
		if ("none".equals(momVO.getOriginSyrup())) {
			momVO.setOriginSyrup(" ");
		}
		if ("none".equals(momVO.getOriginTopping())) {
			momVO.setOriginTopping(" ");
		}

		try {
			totalFlag = mmDao.updateOriginGoods(momVO);

			if ("커피".equals(momVO.getOriginMenuCate().trim()) || "율리치노".equals(momVO.getOriginMenuCate().trim())) {
				if (mmDao.selectGoodsDupli(momVO)) {// 원래 레시피가 존재 (update)
					totalFlag = mmDao.updateOriginRecipe(momVO);
				} else {// 원래 레시피가 존재하지 않는다. (insert)
					System.err
							.println("----------------------------------------------------------------- 원래 레시피가 없는 상품");
					totalFlag = mmDao.insertAddModifyRecipe(momVO);
				} // end else
			} // end if 레시피도 insert
			joFlag.put("updateFlag", totalFlag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joFlag;
	}// originMenuModify

	/**
	 * 검색값을 받아서 검색 값이 없다면 전체글의 개수를 조회하고, 검색 값이 있다면 검색 값에 해당하는 글의 개수를 조회하는 일
	 * 
	 * @param sv
	 * @return
	 */
	public int totalCount(MenuSearchVO msVO) {
		int totalCnt = 0;
		MenuManageDAO mmDao = MenuManageDAO.getInstance();
		try {
			totalCnt = mmDao.selectTotalCount(msVO);
		} catch (SQLException e) {
			e.printStackTrace();
		} // end catch
		return totalCnt;
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
	 * @param pageScale  한 화면에 보여줄 게시물의 수
	 * @param totalCount 총 게시물의 수
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;// 모든 게시물을 보여주기위한 총 페이지 수

		if (totalCount % pageScale != 0) {// pageScale로 딱 떨어지지 않으면 나머지 게시물을 보여주기 위해 1장 더 필요하다.
			totalPage++;
		} // end if
		/* 방법2 */
		/* int totalPage = (int)Math.ceil((double)totalCount/pageScale); */

		return totalPage;
	}// totalPage

	/**
	 * 페이지의 시작번호 구하는 일
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
	 * 페이지의 끝번호 구하는 일
	 * 
	 * @param pageScale
	 * @param startNum
	 * @return
	 */
	public int endNum(int pageScale, int startNum) {
		int endNum = startNum + pageScale - 1;
		return endNum;
	}// endNum

	public JSONArray searchAllMenu(MenuSearchVO msVO) {
		List<MenuAllDomain> selectMenu = null;
		JSONArray menuData = new JSONArray();
		MenuManageDAO mmDao = MenuManageDAO.getInstance();

		try {
			JSONObject jo = null;
			selectMenu = mmDao.selectAllMenu(msVO);

			MenuAllDomain mad = null;
			for (int i = 0; i < selectMenu.size(); i++) {
				mad = selectMenu.get(i);
				jo = new JSONObject();
				jo.put("gdName", mad.getGdName());
				jo.put("gcCate", mad.getGcCate());
				jo.put("gdPrice", mad.getGdPrice());

				menuData.add(jo);
			} // end for

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return menuData;

	}// searchAllMenu

	// 현재 게시판의 페이지 인덱스 설정
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
			strList = strList + "<li class=\"page-item\"><a href=\"#\" class=\"page-link\"" + "onclick='searchAllGoods("
					+ curpage + ",\"" + ilVO.getUrl() + "\");'>" + "aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span></a></li>";
		} else {
			strList = strList + "<li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "</a>\r\n" + "		    </li>";
		} // end else

		// 시작페이지 번호부터 마지막페이지 번호까지 화면에 표시
		curpage = startpage;

		while (curpage <= endpage) {
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='현재페이지'>"
						+ ilVO.getCurrentPage() + "</a></li>";
			} else {
				strList = strList + "<li class=\"page-item\"><a class=\"page-link\"   onclick='searchAllGoods("
						+ curpage + ",\"" + ilVO.getUrl() + "\");'>" + curpage + "</a></li>";
			} // end else

			curpage++;
		} // end while

		// 뒤에 페이지가 더 있는경우
		if (ilVO.getTotalPage() > endpage) {
			curpage = endpage + 1;
			strList = strList + "<li class=\"page-item\">\r\n" + " <a class=\"page-link\"" + "onclick='searchAllGoods("
					+ curpage + ",\"" + ilVO.getUrl() + "\");'>" + "aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span></a>";
		} else {
			strList = strList + "<li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#\" aria-label=\"Next\">\r\n"
					+ "		        <span aria-hidden=\"true\">&raquo;</span>\r\n" + "		      </a>\r\n"
					+ "		    </li>";
		} // end else

		return strList;
	}// indexList

}// class
