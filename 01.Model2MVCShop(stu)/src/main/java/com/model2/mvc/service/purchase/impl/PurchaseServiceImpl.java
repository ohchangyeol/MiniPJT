package com.model2.mvc.service.purchase.impl;

import java.io.PrintWriter;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDao;
	//constructor
	public PurchaseServiceImpl() {
		purchaseDao = new PurchaseDAO();
	}

	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.insertPruchase(purchaseVO);
	}
	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception {
		PurchaseVO getPurchase = purchaseDao.findPruchase(tranNo);
		return getPurchase;
	}
	@Override
	public PurchaseVO getPurchase2 (int ProdNo) throws Exception {
		PurchaseVO getPurchase = purchaseDao.findProduct(ProdNo);
		return getPurchase;
	}
	@Override
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String buyerId) throws Exception {
		HashMap<String, Object> map = purchaseDao.getPruchaseList(searchVO, buyerId);
		return map;
	}
	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		HashMap<String, Object> map = purchaseDao.getSaleList(searchVO);
		return map;
	}
	@Override
	public void updatePurcahse(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.updatePruchase(purchaseVO);
	}
	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		purchaseDao.updateTranCode(purchaseVO);
	}

}
