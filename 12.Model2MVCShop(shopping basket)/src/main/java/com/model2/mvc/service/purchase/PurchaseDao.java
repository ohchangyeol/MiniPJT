package com.model2.mvc.service.purchase;

import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;


//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface PurchaseDao {
	
	// INSERT
	public void insertPruchase (Purchase purchase) throws Exception;	

	// SELECT ONE
	public Purchase findPruchase(int tranNo) throws Exception ;
	
	// SELECT ONE
	public Purchase findProduct (int prodNo) throws Exception ;

	// SELECT LIST
	public List<Purchase> getPruchaseList(Map< String,Object > search) throws Exception ;
	
	// SELECT LIST
	public List<Purchase> getSaleList(Map< String,Object > search) throws Exception ;
	
	// UPDATE 
	public void updatePruchase (Purchase purchase) throws Exception;
	
	// UPDATE
	public void updateTranCode (Purchase purchase) throws Exception;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Map< String,Object > search) throws Exception ;
}