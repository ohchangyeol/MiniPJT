package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDao {

	public PurchaseDao() {}
	
	
	public void insertPruchase (Purchase purchase) throws Exception {		
		// 구매를 위한 DBMS를 수행한다.
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACTION "
				+ "VALUES (seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		stmt.setString(2, purchase.getBuyer().getUserId());
		stmt.setString(3, purchase.getPaymentOption());
		stmt.setString(4, purchase.getReceiverName());
		stmt.setString(5, purchase.getReceiverPhone());
		stmt.setString(6, purchase.getDivyAddr());
		stmt.setString(7, purchase.getDivyRequest());
		stmt.setString(8, "1");
		stmt.setString(9, purchase.getDivyDate());
		stmt.executeUpdate();
		
		con.close();
	}
	public Purchase findPruchase(int tranNo) throws Exception {
		// 구매정보 상세 조회를 위한 DBMS를 수행한다
		System.out.println("=============== findPruchase 실행");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT t.* FROM transaction t, product p WHERE t.prod_no = p.prod_no AND t.tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		stmt.setInt(1, tranNo);
		
		ResultSet rs = stmt.executeQuery();

		rs.next();
		purchase.setTranNo(rs.getInt(1));
		product.setProdNo(rs.getInt(2));
		user.setUserId(rs.getString(3));
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption(rs.getString(4).trim());
		purchase.setReceiverName(rs.getString(5));
		purchase.setReceiverPhone(rs.getString(6));
		purchase.setDivyAddr(rs.getString(7));
		purchase.setDivyRequest(rs.getString(8));
		purchase.setTranCode(rs.getString(9).trim());
		purchase.setOrderDate(rs.getDate(10));
		purchase.setDivyDate(rs.getString(11));
		
		System.out.println("=============== findPruchase 끝!");
		return purchase;
	}
	public Purchase findProduct (int prodNo) throws Exception {
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT t.* FROM transaction t, product p WHERE t.prod_no = p.prod_no AND t.prod_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		stmt.setInt(1, prodNo);
		
		ResultSet rs = stmt.executeQuery();
		// System.out.println("zzzzzzzzz"+rs.getInt(1));
		rs.next();
		purchase.setTranNo(rs.getInt(1));
		product.setProdNo(rs.getInt(2));
		user.setUserId(rs.getString(3));
		purchase.setPurchaseProd(product);
		purchase.setBuyer(user);
		purchase.setPaymentOption(rs.getString(4).trim());
		purchase.setReceiverName(rs.getString(5));
		purchase.setReceiverPhone(rs.getString(6));
		purchase.setDivyAddr(rs.getString(7));
		purchase.setDivyRequest(rs.getString(8));
		purchase.setTranCode(rs.getString(9).trim());
		purchase.setOrderDate(rs.getDate(10));
		purchase.setDivyDate(rs.getString(11));
		
		return purchase;
	}
	public HashMap<String,Object> getPruchaseList(Search search ,String string) throws Exception {
		// 구매 목록 보기를 위한 DBMS 를 수행한다.
		System.out.println("============== getPruchaseList 시작");
		Connection con = DBUtil.getConnection();
		String sql = "SELECT t.* FROM transaction t, product p WHERE t.prod_no = p.prod_no AND t.buyer_id = '"+string+"' ";
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0")) {
				sql += " AND prod_name LIKE '%" + search.getSearchKeyword()
						+ "%'";
			} else if (search.getSearchCondition().equals("1")) {
				sql += " AND prod_no LIKE '%" + search.getSearchKeyword()
						+ "%'";
			}
		}
		sql += " order by p.prod_no";
		
		int totalCount = this.getTotalCount(sql);
		
		sql = makeCurrentPageSql(sql, search);
		
		System.out.println(":: = sql = "+sql);

		PreparedStatement stmt = con.prepareStatement(	sql );
		
		
		ResultSet rs = stmt.executeQuery();
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		while (rs.next()) {
			Purchase pruchase = new Purchase();
			Product product = new Product();
			User user = new User();
			
			System.out.println("zzzzzzzzz"+rs.getInt(1));
			
			pruchase.setTranNo(rs.getInt(1));
			product.setProdNo(rs.getInt(2));
			user.setUserId(rs.getString(3));
			pruchase.setPurchaseProd(product);
			pruchase.setBuyer(user);
			pruchase.setPaymentOption(rs.getString(4).trim());
			pruchase.setReceiverName(rs.getString(5));
			pruchase.setReceiverPhone(rs.getString(6));
			pruchase.setDivyAddr(rs.getString(7));
			pruchase.setDivyRequest(rs.getString(8));
			pruchase.setTranCode(rs.getString(9).trim());
			pruchase.setOrderDate(rs.getDate(10));
			pruchase.setDivyDate(rs.getString(11));
			
			list.add(pruchase);
		}
		map.put("totalCount", totalCount);
		map.put("list", list);
		
		System.out.println(":: = map().size() = "+ map.size());

		rs.close();
		stmt.close();
		con.close();
		System.out.println("============== getPruchaseList end!");
		return map;
	}
	public HashMap<String,Object> getSaleList(Search search ) throws Exception {
		// 판매 목록 보기를 위한 DBMS 를 수행한다.
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.* , t.tran_status_code FROM  product p , transaction t WHERE p.prod_no = t.prod_no (+) ";
		if (search.getSearchCondition() != null  ) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " WHERE prod_name LIKE '%" + search.getSearchKeyword()+"%'";
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " WHERE prod_no LIKE '%" + search.getSearchKeyword()+"%'";
			}
		}
		sql += " ORDER BY prod_name";

		int totalCount = this.getTotalCount(sql);

		sql = makeCurrentPageSql(sql, search);
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();

		System.out.println("로우의 수:" + totalCount);

		HashMap<String,Object> map = new HashMap<String,Object>();

		List<Product> list = new ArrayList<Product>();
		while (rs.next()) {
			Product vo = new Product();
			vo.setProdNo(rs.getInt("prod_no"));
			vo.setFileName(rs.getString("image_file"));
			vo.setManuDate(rs.getString("manufacture_day"));
			vo.setProdDetail(rs.getString("prod_detail"));
			vo.setProdName(rs.getString("prod_name"));
			vo.setRegDate(rs.getDate("reg_date"));
			vo.setPrice(rs.getInt("price"));
			System.out.println("=== tran_status_code = "+rs.getString("tran_status_code"));
			if(rs.getString("tran_status_code") != null) {
				//System.out.println("=== tran_status_code = "+rs.getString("tran_status_code"));
				vo.setProTranCode(rs.getString("tran_status_code").trim());
			}
			System.out.println(":: = vo = "+vo);
			list.add(vo);
		}
		System.out.println("list.size() : "+ list.size());
		map.put("totalCount", totalCount);
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
		
		rs.close();
		stmt.close();
		con.close();
			
		return map;
	}
	

	public void updatePruchase (Purchase purchase) throws Exception {
		// 구매정보 수정을 위한 DBMS를 수행한다.
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction SET payment_option=?,receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=? ,dlvy_date=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchase.getPaymentOption());
		stmt.setString(2, purchase.getReceiverName());
		stmt.setString(3, purchase.getReceiverPhone());
		stmt.setString(4, purchase.getDivyAddr());
		stmt.setString(5, purchase.getDivyRequest());
		stmt.setString(6, purchase.getDivyDate());
		stmt.setInt(7, purchase.getTranNo());
		stmt.executeUpdate();
		
		
		System.out.println("구매정보 수정 완료.");
		stmt.close();
		con.close();
	}
	public void updateTranCode (Purchase purchase) throws Exception {
		// 구매 상태 코드 수정을 위한 DBMS를 수행한다. 
		Connection con = DBUtil.getConnection();
		int tranCode = Integer.parseInt(purchase.getTranCode())+1;
		System.out.println(":: tranCode = " + tranCode );
		
		purchase.setTranCode(tranCode+"");
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchase.getTranCode());
		stmt.setInt(2, purchase.getTranNo());
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
	}
	
	private int getTotalCount(String sql) throws Exception {
		System.out.println("///DAO :: getTotalCount method Start///");
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		System.out.println(":: = sql = "+sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println("= total count에서의 sql  = "+sql);
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
			System.out.println("= totalcount에서의 count = "+totalCount);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		System.out.println("///DAO :: getTotalCount method End///\n");
		return totalCount;
	}
	
	// 게시판 currentPage Row 만  return 
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
