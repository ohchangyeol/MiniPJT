package com.model2.mvc.service.purchase;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;


//==> ȸ���������� ������ ���� �߻�ȭ/ĸ��ȭ�� Service  Interface Definition  
public interface PurchaseService {
	
	// ����
	public void addPurchase(Purchase purchase, Product product) throws Exception;
	
	// ���� �� ��ȸ
	public Purchase getPurchase(int tranNo) throws Exception;
	
	// ����Ҷ� ����
	public Purchase getPurchase2(int ProdNo) throws Exception;
	
	// ����� ���� ��� ��ȸ
	public Map<String,Object> getPurchaseList(Map<String, Object> map) throws Exception;
	
	// ������Ʈ�� ���� �ٽ� ��� ��ȸ �� ���
	public Map<String,Object> getSaleList(Map<String, Object> map) throws Exception;
	
	// ���� ��� ����
	public void updatePurcahse(Purchase purchase) throws Exception;
	
	// ��� ���� ����
	public void updateTranCode(Purchase purchase) throws Exception;
	
	
}