package com.model2.mvc.service.purchase.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class PurchaseServiceTest {

	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		System.out.println("testAddPruchase start ....");
		
		Purchase purchase = new Purchase();
		Product product = new Product();
		User user = new User();
		
		// ��ǰ, ��������
		product.setProdNo(10004);
		user.setUserId("user02");
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("���ݷ�");
		purchase.setReceiverPhone("010-010-010");
		purchase.setDivyAddr("������77");
		purchase.setDivyRequest("������� ��Ź�帳�ϴ�.");
		purchase.setTranCode("1");
		purchase.setDivyDate("21/03/01");
		
		purchaseService.addPurchase(purchase);
		System.out.println(purchase);
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		System.out.println("testGetPurchase start ....");
		Purchase purchase = purchaseService.getPurchase(10000);
		System.out.println(purchase);
		System.out.println("testGetPurchase end ....");
	}
	//@Test
	public void testGetPurchase2() throws Exception {
		System.out.println("testGetPurchase2 start ....");
		Purchase purchase = purchaseService.getPurchase2(10004);
		System.out.println(purchase);
		System.out.println("testGetPurchase2 end ....");
	}
	
	//@Test
	public void getPruchaseListAll() throws Exception {
		System.out.println("getPruchaseListAll start ....");
		Search search = new Search();
		User user = new User();
		Map<String,Object> searchMap = new HashMap<String, Object>();
		
		user.setUserId("user01");
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	searchMap.put("search", search);
	 	searchMap.put("user", user );
	 	System.out.println(searchMap);
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(searchMap);
//	 	System.out.println("map = "+map);
	 	
		System.out.println("getPruchaseListAll end ....");
	}
	
	@Test
	public void getSaleListAll() throws Exception {
		System.out.println("getSaleListAll start ....");
		Search search = new Search();
		User user = new User();
		Map<String,Object> searchMap = new HashMap<String, Object>();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	searchMap.put("search", search);
	 	
	 	Map<String,Object> map = purchaseService.getSaleList(searchMap);
//		 	System.out.println("map = "+map);
	 	
		System.out.println("getSaleListAll end ....");
	}
	
	//@Test
	public void testUpdatePruchase() throws Exception {
		System.out.println("testUpdatePruchase start ....");
		
		Purchase purchase = purchaseService.getPurchase(10000);
		System.out.println("Update �� = " +purchase);
		purchase.setDivyDate("20210817");
		
		purchaseService.updatePurcahse(purchase);
		
		purchase = purchaseService.getPurchase(10000);
		System.out.println("Update �� = " +purchase);

		System.out.println("testUpdatePruchase end ....");
	}
	//@Test
	public void testUpdateTranCode() throws Exception {
		
		Purchase purchase = purchaseService.getPurchase(10000);
		int tranCode = Integer.parseInt(purchase.getTranCode().trim())+1;

		System.out.println("Update �� = " +purchase);
		
		purchase.setTranCode(tranCode+"");
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10000);
		System.out.println("Update �� = " +purchase);

		System.out.println("testUpdatePruchase end ....");
	}
}