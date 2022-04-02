package com.model2.mvc.service.basket.test;

import java.util.ArrayList;
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
import com.model2.mvc.service.domain.Product;
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
		
		List<String> item = new ArrayList<String>();
		item.add("10004:1");
		item.add("10005:3");
		
		System.out.println(item);
		
		
		
	}
	
	
	
	
}