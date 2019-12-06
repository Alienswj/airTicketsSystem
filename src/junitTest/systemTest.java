package junitTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cn.cuit.dao.dbCRUD;
import cn.cuit.utils.dbConnUtil;

@DisplayName("When running a Junit5 test...")
public class systemTest {
	@BeforeEach
	void init() {
		System.out.println("Test starting...");
		dbCRUD.connDb();
	}
	
	@Test
	@Disabled
	void testConn() {
		dbConnUtil.getConnection();
	}
	
	@Test
	void testRetrieve() {
		ResultSet rs=dbCRUD.retrieve("select username,password,userId,accessRight from userinfo where username='shiweijian' and password='9638527410'");
		try {
			while(rs.next())
				System.out.println(rs.getString("username"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
