package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;


//==> ȸ������ ���� ����
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
	
	///Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	//setter
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	///Constructor
	public ProductServiceImpl() {
		System.out.println("@Service :: "+this.getClass());
	}

	///Method
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}

	public Map<String , Object > getProductList(Search search) throws Exception {
		int totalCount = productDao.getTotalCount(search);
		//System.out.println("totalCount = "+totalCount);
		
		List<Product> list= productDao.getProductList(search);
		//System.out.println("list = "+list);
		
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}

	public boolean checkDuplication(int prodNo) throws Exception {
		boolean result=true;
		Product product = productDao.getProduct(prodNo);
		if(product != null) {
			result=false;
		}
		return result;
	}
}