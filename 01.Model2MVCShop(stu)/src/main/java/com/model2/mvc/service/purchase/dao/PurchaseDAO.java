package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {}
	
	public PurchaseVO findPruchase(int tranNo) throws Exception {
		// 구매정보 상세 조회를 위한 DBMS를 수행한다
		System.out.println("=============== findPruchase 실행");
		
		PurchaseVO purchase = new PurchaseVO();
		ProductVO product = new ProductVO();
		UserVO user = new UserVO();
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT t.* FROM transaction t, product p WHERE t.prod_no = p.prod_no AND t.tran_no = ?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		stmt.setInt(1, tranNo);
		
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
		
		System.out.println("=============== findPruchase 끝!");
		return purchase;
	}
	public PurchaseVO findProduct (int prodNo) throws Exception {
		PurchaseVO purchase = new PurchaseVO();
		ProductVO product = new ProductVO();
		UserVO user = new UserVO();
		
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
	public HashMap<String,Object> getPruchaseList(SearchVO search ,String string) throws Exception {
		// 구매 목록 보기를 위한 DBMS 를 수행한다.
		System.out.println("============== getPruchaseList 시작");
		Connection con = DBUtil.getConnection();
		String sql = "SELECT t.* FROM transaction t, product p WHERE t.prod_no = p.prod_no AND t.buyer_id = ? ";
		if (search.getSearchCondition() != null) {
			if (search.getSearchCondition().equals("0")) {
				sql += " AND prod_name='" + search.getSearchKeyword()
						+ "'";
			} else if (search.getSearchCondition().equals("1")) {
				sql += " AND prod_no=" + search.getSearchKeyword()
						+ "";
			}
		}
		sql += " order by p.prod_no";
		
		System.out.println(sql);
		PreparedStatement stmt = 
			con.prepareStatement(	sql,
														ResultSet.TYPE_SCROLL_INSENSITIVE,
														ResultSet.CONCUR_UPDATABLE);
		
		stmt.setString(1, string);
		
		ResultSet rs = stmt.executeQuery();
	
		rs.last();
		int total = rs.getRow();
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("count", new Integer(total));
		rs.absolute(search.getPage() * search.getPageUnit() - search.getPageUnit()+1);
		ArrayList<PurchaseVO> list = new ArrayList<PurchaseVO>();
		if (total > 0) {
			for (int i = 0; i < search.getPageUnit(); i++) {
				PurchaseVO pruchase = new PurchaseVO();
				ProductVO product = new ProductVO();
				UserVO user = new UserVO();
				
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
				if (!rs.next())
					break;
			}
		}
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		con.close();
		System.out.println("============== getPruchaseList end!");
		return map;
	}
	public HashMap<String,Object> getSaleList(SearchVO search ) throws Exception {
		// 판매 목록 보기를 위한 DBMS 를 수행한다.
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT p.* , t.tran_status_code FROM  product p , transaction t WHERE p.prod_no = t.prod_no (+) ";
		if (search.getSearchCondition() != null) {
			
			if (search.getSearchCondition().equals("0")) {
			
				sql += " and p.prod_name='" + search.getSearchKeyword()
						+ "'";
			} else if (search.getSearchCondition().equals("1")) {
				sql += " amd p.prod_no=" + search.getSearchKeyword();
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

		rs.absolute(search.getPage() * search.getPageUnit() - search.getPageUnit()+1);
		System.out.println("searchVO.getPage():" + search.getPage());
		System.out.println("searchVO.getPageUnit():" + search.getPageUnit());
		
		System.out.println("============================================");
		System.out.println("곱하기:" +search.getPage() * search.getPageUnit());
		System.out.println("뺴기 :" +(search.getPageUnit()+1));
		System.out.println("결과 :" + (search.getPage() * search.getPageUnit() - search.getPageUnit()+1));

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for (int i = 0; i < search.getPageUnit(); i++) {
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
					//System.out.println("=== tran_status_code = "+rs.getString("tran_status_code"));
					vo.setProTranCode(rs.getString("tran_status_code").trim());
				}
				System.out.println(":: = vo = "+vo);
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
	public void insertPruchase (PurchaseVO purchaseVO) throws Exception {
		// 구매를 위한 DBMS를 수행한다.
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACTION "
				+ "VALUES (seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, sysdate, ?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setString(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, "1");
		stmt.setString(9, purchaseVO.getDivyDate());
		stmt.executeUpdate();
		
		con.close();
	}
	public void updatePruchase (PurchaseVO purchaseVO) throws Exception {
		// 구매정보 수정을 위한 DBMS를 수행한다.
		Connection con = DBUtil.getConnection();

		String sql = "UPDATE transaction SET payment_option=?,receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=? ,dlvy_date=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getPaymentOption());
		stmt.setString(2, purchaseVO.getReceiverName());
		stmt.setString(3, purchaseVO.getReceiverPhone());
		stmt.setString(4, purchaseVO.getDivyAddr());
		stmt.setString(5, purchaseVO.getDivyRequest());
		stmt.setString(6, purchaseVO.getDivyDate());
		stmt.setInt(7, purchaseVO.getTranNo());
		stmt.executeUpdate();
		
		
		System.out.println("구매정보 수정 완료.");
		stmt.close();
		con.close();
	}
	public void updateTranCode (PurchaseVO purchaseVO) throws Exception {
		// 구매 상태 코드 수정을 위한 DBMS를 수행한다. 
		Connection con = DBUtil.getConnection();
		int tranCode = Integer.parseInt(purchaseVO.getTranCode())+1;
		System.out.println(":: tranCode = " + tranCode );
		
		purchaseVO.setTranCode(tranCode+"");
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getTranNo());
		stmt.executeUpdate();
		
		stmt.close();
		con.close();
	}

}
