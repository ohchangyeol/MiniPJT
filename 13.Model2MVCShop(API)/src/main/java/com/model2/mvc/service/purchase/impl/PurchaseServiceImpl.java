package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.purchase.PurchaseDao;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.UserDao;;


//==> 회원관리 서비스 구현
@Service("purchaseServiceImpl")
public class PurchaseServiceImpl implements PurchaseService{
	
	///Field
	@Autowired
	@Qualifier("purchaseDaoImpl")
	private PurchaseDao purchaseDao;
	

	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	//setter
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	public void setUserDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	///Constructor
	public PurchaseServiceImpl() {
		System.out.println("@Service :: "+this.getClass());
	}

	///Method
	public void addPurchase(Purchase purchase, Product product) throws Exception {
		purchaseDao.insertPruchase(purchase);
		productDao.updateProductCount(product);
	}

	public Purchase getPurchase(int tranNo) throws Exception {
		return purchaseDao.findPruchase(tranNo);
	}

	public Purchase getPurchase2(int ProdNo) throws Exception {
		return purchaseDao.findProduct(ProdNo);
	}

	public Map<String, Object> getPurchaseList(Map<String, Object> searchMap) throws Exception {
		// TODO Auto-generated method stub
		int totalCount = purchaseDao.getTotalCount(searchMap);
		List<Purchase> list = purchaseDao.getPruchaseList(searchMap);
		
		System.out.println("totalCount = "+totalCount);
		System.out.println("list = "+list);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		return map;
	}

	public Map<String, Object> getSaleList(Map<String, Object> searchMap) throws Exception {
		System.out.println("getSaleList 실행 ");
		int totalCount = purchaseDao.getTotalCount(searchMap);
		System.out.println("totalCount = "+totalCount);
		
		List<Purchase> list= purchaseDao.getSaleList(searchMap);
		System.out.println("list = "+list);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		return null;
	}

	public void updatePurcahse(Purchase purchase) throws Exception {
		purchaseDao.updatePruchase(purchase);
		
	}

	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
		
	}
	
	public boolean checkDuplication(int tranCode) throws Exception {
		boolean result=true;
		Purchase purchase=purchaseDao.findPruchase(tranCode);
		if(purchase != null) {
			result=false;
		}
		return result;
	}

}