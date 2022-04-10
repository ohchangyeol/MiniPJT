
package com.model2.mvc.service.domain;

public class Basket {
	
	// Field
	private int ketNo;
	private int prodNo;
	private String buyerId;
	
	public Basket() {
	}
	
	
	public Basket(int prodNo, String buyerId) {
		this.prodNo = prodNo;
		this.buyerId = buyerId;
	}


	// getter, setter
	public int getKetNo() {
		return ketNo;
	}
	public void setKetNo(int ketNo) {
		this.ketNo = ketNo;
	}
	public int getProdNo() {
		return prodNo;
	}
	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	
	@Override
	public String toString() {
		return "Basket [ketNo=" + ketNo + ", prodNo=" + prodNo + ", buyerId=" + buyerId + "]";
	}
	
	
	
}
