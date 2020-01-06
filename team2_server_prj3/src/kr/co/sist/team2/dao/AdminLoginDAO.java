package kr.co.sist.team2.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kr.co.sist.team2.domain.AdminLoginDomain;
import kr.co.sist.team2.vo.AdminLoginVO;

public class AdminLoginDAO {
	
	private static AdminLoginDAO alDao;
	public static SqlSessionFactory ssf;
	
	public static AdminLoginDAO getInstance() {
		if(alDao==null) {
			alDao=new AdminLoginDAO();
		}//if
		return alDao;
	}//instance
	
	public SqlSessionFactory getSessionFactory()throws IOException {
		if(ssf==null) {
			
			Reader reader=null;
			try {
				//1.설정용 xml을 스트림으로 연결
				reader= Resources.getResourceAsReader("kr/co/sist/team2/dao/mybatis-config.xml");
				//2. sqlsessionfactorybuilder 생성
				SqlSessionFactoryBuilder ssfb=new SqlSessionFactoryBuilder();
				//3. sqlsessionfactory 얻ㄱㅣ
				ssf=ssfb.build(reader);
				
			} finally {
				if (reader!=null) { reader.close();}//if
			}//finally
		}
		return ssf;
	}
	
	public boolean selectAdminLogin(AdminLoginVO alVO)throws SQLException{
		
		boolean login_flag=false;
		AdminLoginDomain aldtemp=null;
		SqlSession ss;
		
		try {
			ss=getSessionFactory().openSession();
			aldtemp=ss.selectOne("selectAdminLogin", alVO);
			if(aldtemp!=null){
				login_flag=true;
			}
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return login_flag;		
		
	}//selectAdminLogin
	
	public int updateDate(String date) throws SQLException {
		int cnt = 0;
		SqlSession ss;
		try {
			ss = getSessionFactory().openSession();
			cnt = ss.update("updateDate", date);
			if (cnt == 1) {
				ss.commit();
			}
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
}//class
