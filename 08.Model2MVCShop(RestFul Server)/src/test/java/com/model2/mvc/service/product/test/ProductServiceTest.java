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
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
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
		
		product.setProdName("������");
//		product.setProdDetail("�ʹ��ʹ�����Ŀ�.");
//		product.setManuDate("20010220");
//		product.setPrice(20000);
//		product.setFileName("��������.jpg");
		
		System.out.println(product);
		
		productService.addProduct(product);
		Assert.assertEquals("������", product.getProdName());
		
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
		
		System.out.println("Update �� = "+product);
		
		product.setPrice(200000);
		product.setProdDetail("���� ������ ¯¯�̿���.");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10004);
		System.out.println("Update �� = "+product);
	 }
	 
	//@Test
	public void testCheckDuplication() throws Exception{

		System.out.println(productService.checkDuplication(10002));
		//System.out.println(productService.checkDuplication(10002 +System.currentTimeMillis()) );
	 	
		//==> API Ȯ��
		//Assert.assertFalse( productService.checkDuplication("testUserId") );
	 	//Assert.assertTrue( productService.checkDuplication("testUserId"+System.currentTimeMillis()) );
		 	
	}
	
	 //==>  �ּ��� Ǯ�� �����ϸ�....
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
	 	search.setSearchKeyword("�Ｚ");
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
//		//==> console Ȯ��
	 	System.out.println(list);
//	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }	 
}