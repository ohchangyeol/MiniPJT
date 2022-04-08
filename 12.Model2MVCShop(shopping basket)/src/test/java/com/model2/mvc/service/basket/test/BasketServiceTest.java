package com.model2.mvc.service.basket.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.BuyBasket;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })

public class BasketServiceTest {

	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;

//	@Test
	public void testAddBasket() throws Exception {
		
		Basket basket = new Basket();
		basket.setBuyerId("user01");
		basket.setProdNo(10004);
		
		System.out.println(basketService.addBasket(basket));
	}
	
//	@Test
	public void testDeleteBasket() throws Exception {
		
		System.out.println("Delete Basket start");
		Basket basket = new Basket();
		basket.setBuyerId("user01");
//		basket.setProdNo(10003);
		
		System.out.println(basketService.deleteBasket(basket));
	}
	
//	@Test
	public void testGetBasketList() throws Exception{
		
		System.out.println("getList Basket start");
		
		List<Product> list = basketService.getBasketList("user02");
		System.out.println(list.size());
		
		for (Product product : list) {
			System.out.println( product );
		}
	}
	
	@Test
	public void testAddbuyBasket () throws Exception{
		
		System.out.println("buy Basket start");
		
		BuyBasket buyBasket = new BuyBasket();

		User user = new User();
		user.setUserId("user01");
		
		Purchase purchase = new Purchase();
		purchase.setPaymentOption("1");
		purchase.setReceiverName("테스트 닉네임");
		purchase.setReceiverPhone("01093483922");
		purchase.setDivyAddr("주소");
		purchase.setDivyRequest("요청사항입니다");
		purchase.setTranCode("1");
		purchase.setDivyDate("22/05/23");
		purchase.setBuyer(user);
		
		buyBasket.setPurchase(purchase);
		
		
		List<String> item = new ArrayList<String>();
		item.add("10004:5");
		item.add("10005:4");
		item.add("10007:3");
		item.add("10008:2");
		item.add("10009:1");
		
		System.out.println(item);
		
		for (String temp : item) {
			
			String[] str = temp.split(":");
			
			for (int i = 0; i < str.length; i++) {
				
				if(i == 0) {
					buyBasket.getProdNo().add(str[i]);
//					System.out.println(i + " = " + str[i]);
				}else {
					buyBasket.getProdCount().add(str[i]);
//					System.out.println(i + " = " + str[i]);
				}
				
			}
			
		}
		
		System.out.println(buyBasket.getProdNo());
		System.out.println(buyBasket.getProdCount());
		System.out.println(buyBasket.getPurchase());
		
		basketService.addBasketList(buyBasket);
		

	}
	
	
	
	
}