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

		// BookInsertVO�� img�� null�̱� ������ ���ϸ��� ������ set�Ͽ� �ݴϴ�.
		mvaVO.setNewMenuImgselect(multipartFile.getOriginalFilename());

		String category = "";
		if ("Ŀ��".equals(mvaVO.getModalSelectCate().trim())) {
			category = "coffee";
		} else if ("����ġ��".equals(mvaVO.getModalSelectCate().trim())) {
			category = "yuliccino";
		} else if ("Ƽ".equals(mvaVO.getModalSelectCate().trim())) {
			category = "tea";
		} else if ("����Ʈ".equals(mvaVO.getModalSelectCate().trim())) {
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

			int readDate = 0; // ����Ʈ�� �����ϴ� ���� ����
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

			if ("����".equals(moid.getmName().trim()) && "Ŀ��".equals(moid.getGcName().trim())
					|| "����ġ��".equals(moid.getGcName().trim())) {
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

		// BookInsertVO�� img�� null�̱� ������ ���ϸ��� ������ set�Ͽ� �ݴϴ�.
		momVO.setOriginMenuImg(multipartFileModify.getOriginalFilename());

		String category = "";
		if ("Ŀ��".equals(momVO.getOriginMenuCate().trim())) {
			category = "coffee";
		} else if ("����ġ��".equals(momVO.getOriginMenuCate().trim())) {
			category = "yuliccino";
		} else if ("Ƽ".equals(momVO.getOriginMenuCate().trim())) {
			category = "tea";
		} else if ("����Ʈ".equals(momVO.getOriginMenuCate().trim())) {
			category = "dessert";
		}

		// //2. ��� ����
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

			int readDate = 0; // ����Ʈ�� �����ϴ� ���� ����
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

			if ("Ŀ��".equals(momVO.getOriginMenuCate().trim()) || "����ġ��".equals(momVO.getOriginMenuCate().trim())) {
				if (mmDao.selectGoodsDupli(momVO)) {// ���� �����ǰ� ���� (update)
					totalFlag = mmDao.updateOriginRecipe(momVO);
				} else {// ���� �����ǰ� �������� �ʴ´�. (insert)
					System.err
							.println("----------------------------------------------------------------- ���� �����ǰ� ���� ��ǰ");
					totalFlag = mmDao.insertAddModifyRecipe(momVO);
				} // end else
			} // end if �����ǵ� insert
			joFlag.put("updateFlag", totalFlag);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return joFlag;
	}// originMenuModify

	/**
	 * �˻����� �޾Ƽ� �˻� ���� ���ٸ� ��ü���� ������ ��ȸ�ϰ�, �˻� ���� �ִٸ� �˻� ���� �ش��ϴ� ���� ������ ��ȸ�ϴ� ��
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
	 * @param pageScale  �� ȭ�鿡 ������ �Խù��� ��
	 * @param totalCount �� �Խù��� ��
	 * @return
	 */
	public int totalPage(int pageScale, int totalCount) {
		int totalPage = totalCount / pageScale;// ��� �Խù��� �����ֱ����� �� ������ ��

		if (totalCount % pageScale != 0) {// pageScale�� �� �������� ������ ������ �Խù��� �����ֱ� ���� 1�� �� �ʿ��ϴ�.
			totalPage++;
		} // end if
		/* ���2 */
		/* int totalPage = (int)Math.ceil((double)totalCount/pageScale); */

		return totalPage;
	}// totalPage

	/**
	 * �������� ���۹�ȣ ���ϴ� ��
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
	 * �������� ����ȣ ���ϴ� ��
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

	// ���� �Խ����� ������ �ε��� ����
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
			strList = strList + "<li class=\"page-item\"><a href=\"#\" class=\"page-link\"" + "onclick='searchAllGoods("
					+ curpage + ",\"" + ilVO.getUrl() + "\");'>" + "aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span></a></li>";
		} else {
			strList = strList + "<li class=\"page-item\">\r\n"
					+ "		      <a class=\"page-link\" href=\"#\" aria-label=\"Previous\">\r\n"
					+ "		        <span aria-hidden=\"true\">&laquo;</span>\r\n" + "</a>\r\n" + "		    </li>";
		} // end else

		// ���������� ��ȣ���� ������������ ��ȣ���� ȭ�鿡 ǥ��
		curpage = startpage;

		while (curpage <= endpage) {
			if (curpage == ilVO.getCurrentPage()) {
				strList = strList + "<li class=\"page-item\"><a class='page-link' title='����������'>"
						+ ilVO.getCurrentPage() + "</a></li>";
			} else {
				strList = strList + "<li class=\"page-item\"><a class=\"page-link\"   onclick='searchAllGoods("
						+ curpage + ",\"" + ilVO.getUrl() + "\");'>" + curpage + "</a></li>";
			} // end else

			curpage++;
		} // end while

		// �ڿ� �������� �� �ִ°��
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
