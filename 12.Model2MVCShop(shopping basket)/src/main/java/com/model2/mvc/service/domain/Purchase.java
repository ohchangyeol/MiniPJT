package com.model2.mvc.service.domain;


import java.sql.Date;



public class Purchase {
	
	private int tranNo;
	private Product purchaseProd;
	private User buyer;
	private String paymentOption;
	private String receiverName;
	private String receiverPhone;
	private String divyAddr;
	private String divyRequest;
	private String tranCode;
	private Date orderDate;
	private String divyDate;
	
	// regDate String
	private String orderDateString;
	
	// 구매 수량
	private int tranCount;
	// 장바구니 코드
	private int basketCode;
	// 총 결제 금액
	private int totalPrice;
	
	public String getOrderDateString() {
			return orderDateString;
	}

	public void setOrderDateString(String orderDateString) {
		this.orderDateString = orderDateString;
	}

	public Purchase(){}
	
	public void setBuyer(User buyer) {this.buyer = buyer;}
	public void setDivyAddr(String divyAddr) {this.divyAddr = divyAddr;}
	public void setDivyDate(String divyDate) {this.divyDate = divyDate;}
	public void setDivyRequest(String divyRequest) {this.divyRequest = divyRequest;}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
		if(orderDate != null) {
			this.setOrderDateString( orderDate.toString().split("-")[0]
					+"-"+ orderDate.toString().split("-")[1]
							+ "-" +orderDate.toString().split("-")[2] );
		}
	}
	public void setPaymentOption(String paymentOption) {this.paymentOption = paymentOption;}
	public void setPurchaseProd(Product purchaseProd) {this.purchaseProd = purchaseProd;}
	public void setReceiverName(String receiverName) {this.receiverName = receiverName;}
	public void setReceiverPhone(String receiverPhone) {this.receiverPhone = receiverPhone;}
	public void setTranCode(String tranCode) {this.tranCode = tranCode;}
	public void setTranNo(int tranNo) {this.tranNo = tranNo;}
		
	public User getBuyer() {return buyer;}
	public String getDivyAddr() {return divyAddr;}
	public String getDivyDate() {return divyDate;}
	public String getDivyRequest() {return divyRequest;}
	public Date getOrderDate() {return orderDate;}
	public String getPaymentOption() {return paymentOption;}
	public Product getPurchaseProd() {return purchaseProd;}
	public String getReceiverName() {return receiverName;}
	public String getReceiverPhone() {return receiverPhone;}
	public String getTranCode() {return tranCode;}
	public int getTranNo() {return tranNo;}
	
	
	public int getTranCount() {
		return tranCount;
	}

	public void setTranCount(int tranCount) {
		this.tranCount = tranCount;
	}

	
	public int getBasketCode() {
		return basketCode;
	}

	public void setBasketCode(int basketCode) {
		this.basketCode = basketCode;
	}

	
	
	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Purchase [tranNo=" + tranNo + ", purchaseProd=" + purchaseProd + ", buyer=" + buyer + ", paymentOption="
				+ paymentOption + ", receiverName=" + receiverName + ", receiverPhone=" + receiverPhone + ", divyAddr="
				+ divyAddr + ", divyRequest=" + divyRequest + ", tranCode=" + tranCode + ", orderDate=" + orderDate
				+ ", divyDate=" + divyDate + ", orderDateString=" + orderDateString + ", tranCount=" + tranCount
				+ ", basketCode=" + basketCode + ", totalPrice=" + totalPrice + "]";
	}

	

	

	
}