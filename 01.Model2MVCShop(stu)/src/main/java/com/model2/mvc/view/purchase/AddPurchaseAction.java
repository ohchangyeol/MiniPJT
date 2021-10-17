package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class AddPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseAction Ω√¿€");
		
		PurchaseVO purchase = new PurchaseVO();
		UserVO user = new UserVO();
		user.setUserId(request.getParameter("buyerId"));
		
		ProductVO product = new ProductVO();
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