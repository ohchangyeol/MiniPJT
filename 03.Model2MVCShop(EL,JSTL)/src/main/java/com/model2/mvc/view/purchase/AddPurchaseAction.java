package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseAction Ω√¿€");
		
		Purchase purchase = new Purchase();
		User user = new User();
		Product product = new Product();

		user.setUserId(request.getParameter("buyerId"));
		
		product.setProdNo(Integer.parseInt(request.getParameter("prodNo")));
		
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		
		PurchaseService service = new PurchaseServiceImpl();
		service.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		System.out.println("================AddPurchaseAction ≥°");
		return "forward:/purchase/addPurchase.jsp";
	}
}