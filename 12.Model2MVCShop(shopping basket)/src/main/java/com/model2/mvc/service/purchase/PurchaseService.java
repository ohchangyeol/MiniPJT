package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;


//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition  
public interface PurchaseService {
	
	// 구매
	public void addPurchase(Purchase purchase, Product product) throws Exception;
	
	// 구매 상세 조회
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// 배송할때 쓰임
	public Purchase getPurchase2(int ProdNo) throws Exception;
	
	// 사용자 구매 목록 조회
	public Map<String,Object> getPurchaseList(Map<String, Object> map) throws Exception;
	
	// 업데이트를 통해 다시 목록 조회 시 사용
	public Map<String,Object> getSaleList(Map<String, Object> map) throws Exception;
	
	// 구매 목록 수정
	public void updatePurcahse(Purchase purchase) throws Exception;
	
	// 배송 정보 수정
	public void updateTranCode(Purchase purchase) throws Exception;
	
	
}