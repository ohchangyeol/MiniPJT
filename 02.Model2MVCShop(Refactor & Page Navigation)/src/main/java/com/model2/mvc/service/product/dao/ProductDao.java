package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;


public class ProductDao {
	
	public ProductDao(){
	}

	public void insertProduct (Product product) throws Exception {
		System.out.println("///DAO :: updateUser method Start///");
		Connection con = DBUtil.getConnection();

		String sql = "insert into product values (seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.executeUpdate();
		
		con.close();
	}
	public Product getProduct(int prodNo) throws Exception {
			
		Connection con = DBUtil.getConnection();

		String sql = "select * from product where prod_no=?";
		System.out.println(prodNo);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		Product product = null;
		while (rs.next()) {
			product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setFileName(rs.getString("image_file"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setProdName(rs.getString("prod_name"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setPrice(rs.getInt("price"));
		}
		
		con.close();

		return product;
	}
	public Map<String , Object> getProductList(Search search) throws Exception {
		System.out.println("///DAO :: getProductList method Start///");
		
		Map<String , Object>  map = new HashMap<String, Object>();
		
		Connection con = DBUtil.getConnection();
		
		// Original Query 구성
		String sql = "SELECT p.* , t.tran_status_code FROM  product p , transaction t WHERE p.prod_no = t.prod_no (+) ";
		
		if (search.getSearchCondition() != null  ) {
			if ( search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("") ) {
				sql += " and prod_name LIKE '%" + search.getSearchKeyword()+"%'";
			} else if ( search.getSearchCondition().equals("1") && !search.getSearchKeyword().equals("")) {
				sql += " amd prod_no LIKE '%" + search.getSearchKeyword()+"%'";
			}
		}
		sql += " ORDER BY prod_name";
		
		System.out.println("= Original SQL :: " + sql);
		
		//==> TotalCount GET
		int totalCount = this.getTotalCount(sql);
		
		System.out.println("= totalCount  :: " + totalCount);
		
		System.out.println("= getTotalCount에 갔다온 SQL :: " + sql);
		
		//==> CurrentPage 게시물만 받도록 Query 다시구성
		sql = makeCurrentPageSql(sql, search);
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
	
		System.out.println(search);
		System.out.println(":: = sql = "+sql);

		List<Product> list = new ArrayList<Product>();
		
		while(rs.next()){
			Product product = new Product();
			product.setProdNo(rs.getInt("prod_no"));
			product.setFileName(rs.getString("image_file"));
			product.setManuDate(rs.getString("manufacture_day"));
			product.setProdDetail(rs.getString("prod_detail"));
			product.setProdName(rs.getString("prod_name"));
			product.setRegDate(rs.getDate("reg_date"));
			product.setPrice(rs.getInt("price"));
//			System.out.println("---------------");
//			System.out.println("list에 들어갈 product = " + product);
			if(rs.getString("tran_status_code") != null) {
				product.setProTranCode(rs.getString("tran_status_code").trim());
			}
			list.add(product);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage 의 게시물 정보 갖는 List 저장
		map.put("list", list);

		rs.close();
		pStmt.close();
		con.close();

		System.out.println("///DAO :: getProductList method End///\n");
		return map;
	}
	public void updateProduct(Product product) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE product SET PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? WHERE PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, product.getProdName());
		stmt.setString(2, product.getProdDetail());
		stmt.setString(3, product.getManuDate());
		stmt.setInt(4, product.getPrice());
		stmt.setString(5, product.getFileName());
		stmt.setInt(6, product.getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}
	
	
	// Util
	private int getTotalCount(String sql) throws Exception {
		System.out.println("///DAO :: getTotalCount method Start///");
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
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