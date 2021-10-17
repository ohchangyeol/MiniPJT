package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDao;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDao purchaseDao;
	//constructor
	public PurchaseServiceImpl() {
		purchaseDao = new PurchaseDao();
	}

	@Override
	public void addPurchase(Purchase purchase) throws Exception {
		purchaseDao.insertPruchase(purchase);
	}
	@Override
	public Purchase getPurchase(int tranNo) throws Exception {
		Purchase getPurchase = purchaseDao.findPruchase(tranNo);
		return getPurchase;
	}
	@Override
	public Purchase getPurchase2 (int ProdNo) throws Exception {
		Purchase getPurchase = purchaseDao.findProduct(ProdNo);
		return getPurchase;
	}
	@Override
	public Map<String, Object> getPurchaseList(Search search, String buyerId) throws Exception {
		HashMap<String, Object> map = purchaseDao.getPruchaseList(search, buyerId);
		return map;
	}
	@Override
	public HashMap<String, Object> getSaleList(Search search) throws Exception {
		HashMap<String, Object> map = purchaseDao.getSaleList(search);
		return map;
	}
	@Override
	public void updatePurcahse(Purchase purchase) throws Exception {
		purchaseDao.updatePruchase(purchase);
	}
	@Override
	public void updateTranCode(Purchase purchase) throws Exception {
		purchaseDao.updateTranCode(purchase);
	}

}
