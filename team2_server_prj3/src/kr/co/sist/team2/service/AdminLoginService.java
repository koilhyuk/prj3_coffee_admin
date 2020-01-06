package kr.co.sist.team2.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import kr.co.sist.team2.dao.AdminLoginDAO;
import kr.co.sist.team2.vo.AdminLoginVO;
import kr.co.sist.util.cipher.DataEncrypt;

public class AdminLoginService {
	
	public JSONObject adminLoginChk(AdminLoginVO alVO) {
		JSONObject jo=new JSONObject();
		AdminLoginDAO alDao=AdminLoginDAO.getInstance();
		boolean login_flag=false;
		String adminPass=alVO.getAdminPass();
		String shaPass="";
		
		try {
			shaPass=DataEncrypt.messageDigest("SHA-512", adminPass);
			alVO.setAdminPass(shaPass);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}

		try {
			login_flag =alDao.selectAdminLogin(alVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jo.put("login_flag",login_flag);
		return jo;
		
	}
	public boolean modifyDate(String date) {
		boolean date_flag = false;
		
		AdminLoginDAO alDao=AdminLoginDAO.getInstance();
		try {
			date_flag=alDao.updateDate(date)==1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return date_flag;
	}
	

}
