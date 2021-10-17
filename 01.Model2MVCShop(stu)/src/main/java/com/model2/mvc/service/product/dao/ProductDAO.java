package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductDAO {
	
	public ProductDAO(){
	}

	public void insertProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "insert into product values (seq_product_prod_no.NEXTVAL,?,?,?,?,?,sysdate)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.executeUpdate();
		
		con.close();
	}

	
	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.* , t.tran_status_code FROM  product p , transaction t WHERE p.prod_no = t.prod_no (+) ";
		if (searchVO.getSearchCondition() != null) {
			
			if (searchVO.getSearchCondition().equals("0")) {
			
				sql += " and p.prod_name='" + searchVO.getSearchKeyword()
						+ "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " amd p.prod_no=" + searchVO.getSearchKeyword()
						+ "";
			}
		}
		sql += " order by prod_name";

		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = stmt.executeQuery();

		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수:" + total);

		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + searchVO.getPage());
		System.out.println("searchVO.getPageUnit():" + searchVO.getPageUnit());
		
		System.out.println("============================================");
		System.out.println("곱하기:" +searchVO.getPage() * searchVO.getPageUnit());
		System.out.println("뺴기 :" +(searchVO.getPageUnit()+1));
		System.out.println("결과 :" + (searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1));

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("prod_no"));
				vo.setFileName(rs.getString("image_file"));
				vo.setManuDate(rs.getString("manufacture_day"));
				vo.setProdDetail(rs.getString("prod_detail"));
				vo.setProdName(rs.getString("prod_name"));
				vo.setRegDate(rs.getDate("reg_date"));
				vo.setPrice(rs.getInt("price"));
				System.out.println("=== tran_status_code = "+rs.getString("tran_status_code"));
				if(rs.getString("tran_status_code") != null) {
					vo.setProTranCode(rs.getString("tran_status_code").trim());
				}
				list.add(vo);
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
			
		return map;
	}
	
	
	public ProductVO getProduct(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "select * from product where prod_no=?";
		System.out.println(prodNo);
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setRegDate(rs.getDate("reg_date"));
			productVO.setPrice(rs.getInt("price"));
		}
		
		con.close();

		return productVO;
	}

	
	public void updateProduct(ProductVO productVO) throws Exception {
		
		Connection con = DBUtil.getConnection();

		String sql = "update product set PROD_NAME=?,PROD_DETAIL=?,MANUFACTURE_DAY=?,PRICE=?,IMAGE_FILE=? where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, productVO.getProdName());
		stmt.setString(2, productVO.getProdDetail());
		stmt.setString(3, productVO.getManuDate());
		stmt.setInt(4, productVO.getPrice());
		stmt.setString(5, productVO.getFileName());
		stmt.setInt(6, productVO.getProdNo());
		stmt.executeUpdate();
		
		System.out.println("수정.");
		con.close();
	}
	
}