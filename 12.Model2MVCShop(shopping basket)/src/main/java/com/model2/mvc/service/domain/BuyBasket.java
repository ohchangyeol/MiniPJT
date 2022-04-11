package com.model2.mvc.service.domain;

import java.util.ArrayList;
import java.util.List;

public class BuyBasket {
	
	private Purchase purchase;
	private List<String> prodNo = new ArrayList<String>();
	private List<String> prodCount = new ArrayList<String>();
	
	
	
	public BuyBasket() {
		prodNo = new ArrayList<String>();
		prodCount = new ArrayList<String>();
	}
	
	
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public List<String> getProdNo() {
		return prodNo;
	}
	public void setProdNo(List<String> prodNo) {
		this.prodNo = prodNo;
	}
	public List<String> getProdCount() {
		return prodCount;
	}
	public void setProdCount(List<String> prodCount) {
		this.prodCount = prodCount;
	}


	@Override
	public String toString() {
		return "BuyBasket [purchase=" + purchase + ", prodNo=" + prodNo + ", prodCount=" + prodCount + "]";
	}
	
	
	
	
	
	
}

