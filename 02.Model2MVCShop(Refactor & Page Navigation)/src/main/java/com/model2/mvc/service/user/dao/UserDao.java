package com.model2.mvc.service.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.User;


public class UserDao {
	
	///Field
	
	///Constructor
	public UserDao() {
	}

	///Method
	public void insertUser(User user) throws Exception {
		System.out.println("///DAO :: insertUser method Start///");
		
		Connection con = DBUtil.getConnection();
		String sql = 	"INSERT "+ 
								"INTO USERS "+ 
								"VALUES (?,?,?,'user',?,?,?,?,SYSDATE)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, user.getUserId());
		pStmt.setString(2, user.getUserName());
		pStmt.setString(3, user.getPassword());
		pStmt.setString(4, user.getSsn());
		pStmt.setString(5, user.getPhone());
		pStmt.setString(6, user.getAddr());
		pStmt.setString(7, user.getEmail());
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		System.out.println("///DAO :: InsertUser method End///\n");
	}

	public User findUser(String userId) throws Exception {
		System.out.println("///DAO :: findUser method Start///");
		
		Connection con = DBUtil.getConnection();
			
		String sql = 	"SELECT "+
								"user_id ,  user_name ,  password , role , cell_phone ,  addr ,  email , reg_date " + 
								"FROM users WHERE user_id = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, userId);

		ResultSet rs = pStmt.executeQuery();

		User user = null;

		while (rs.next()) {
			user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setRole(rs.getString("role"));
			user.setPhone(rs.getString("cell_phone"));
			user.setAddr(rs.getString("addr"));
			user.setEmail(rs.getString("email"));
			user.setRegDate(rs.getDate("reg_date"));
		}
		
		rs.close();
		pStmt.close();
		con.close();
		
		System.out.println("///DAO :: findUser method End///\n");
		return user;
	}

	public Map<String , Object> getUserList(Search search) throws Exception {
		System.out.println("///DAO :: getUserList method Start///");
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query ����
		String sql = "SELECT user_id ,  user_name , email  FROM  users ";
		
		
		if (search.getSearchCondition() != null  ) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE user_id LIKE '%" + search.getSearchKeyword()+"%'";
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE user_name LIKE '%" + search.getSearchKeyword()+"%'";
			}
		}
		sql += " ORDER BY user_id";
		
		System.out.println("= Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		
		System.out.println("= totalCount  :: " + totalCount);
		
		System.out.println("= getTotalCount�� ���ٿ� SQL :: " + sql);
		
		//==> CurrentPage �Խù��� �޵��� Query �ٽñ���
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);

		List<User> list = new ArrayList<User>();
		
		while(rs.next()){
			User user = new User();
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setEmail(rs.getString("email"));
			list.add(user);
		}
		
		//==> totalCount ���� ����
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage �� �Խù� ���� ���� List ����
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		System.out.println("///DAO :: getUserList method End///\n");
		return map;
	}

	public void updateUser(User vo) throws Exception {
		System.out.println("///DAO :: updateUser method Start///");

		Connection con = DBUtil.getConnection();

		String sql = 	"UPDATE users "+
								"SET user_name = ?, cell_phone = ? , addr = ? , email = ? "+ 
								"WHERE user_id = ?";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, vo.getUserName());
		pStmt.setString(2, vo.getPhone());
		pStmt.setString(3, vo.getAddr());
		pStmt.setString(4, vo.getEmail());
		pStmt.setString(5, vo.getUserId());
		pStmt.executeUpdate();
		
		pStmt.close();
		con.close();
		System.out.println("///DAO :: updateUser method End///\n");
	}
	
	// �Խ��� Page ó���� ���� ��ü Row(totalCount)  return
	private int getTotalCount(String sql) throws Exception {
		System.out.println("///DAO :: getTotalCount method Start///");
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println("= total count������ sql  = "+sql);
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
			System.out.println("= totalcount������ count = "+totalCount);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		System.out.println("///DAO :: getTotalCount method End///\n");
		return totalCount;
	}
	
	// �Խ��� currentPage Row ��  return 
	private String makeCurrentPageSql(String sql , Search search){
		System.out.println("///DAO :: makeCurrentPageSql method Start///");
		
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+search.getCurrentPage()*search.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1) +" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("UserDAO :: make SQL :: "+ sql);	
		
		System.out.println("///DAO :: makeCurrentPageSql method End///\n");
		
		return sql;
	}
}