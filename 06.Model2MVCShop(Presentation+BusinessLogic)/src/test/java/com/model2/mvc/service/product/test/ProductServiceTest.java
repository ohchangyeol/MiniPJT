package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
										"classpath:config/context-aspect.xml",
										"classpath:config/context-mybatis.xml",
										"classpath:config/context-transaction.xml" })
public class ProductServiceTest {

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		System.out.println("= test Add Product Start...");
		Product product = new Product();
		
		product.setProdName("선생님");
//		product.setProdDetail("너무너무배고파요.");
//		product.setManuDate("20010220");
//		product.setPrice(20000);
//		product.setFileName("가나ㄷㅏ.jpg");
		
		System.out.println(product);
		
		productService.addProduct(product);
		Assert.assertEquals("선생님", product.getProdName());
		
		System.out.println("test Add Product End...");
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		System.out.println("= test Get Product Start...");
		
		Product product = productService.getProduct(10020);
		
		System.out.println(product);
		System.out.println("test Get Product End...");
		
	}
	
	//@Test
	 public void testUpdateProduct() throws Exception{
		 
		Product product = productService.getProduct(10004);
		
		System.out.println("Update 전 = "+product);
		
		product.setPrice(200000);
		product.setProdDetail("완전 내구성 짱짱이에요.");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10004);
		System.out.println("Update 후 = "+product);
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		System.out.println(productService.checkDuplication(10002));
		//System.out.println(productService.checkDuplication(10002 +System.currentTimeMillis()) );
	 	
		//==> API 확인
		//Assert.assertFalse( productService.checkDuplication("testUserId") );
	 	//Assert.assertTrue( productService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
	}
	
	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
//	 	
	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
	 	System.out.println(list);
//	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 }
	 
	 //@Test
	 public void testGetProductListProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("삼성");
	 	Map<String,Object> map = productService.getProductList(search);
	 }
	 
	 //@Test
	 public void testGetProductListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("10004");
	 	Map<String,Object> map = productService.getProductList(search);
//	 	
	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
//		//==> console 확인
	 	System.out.println(list);
//	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}