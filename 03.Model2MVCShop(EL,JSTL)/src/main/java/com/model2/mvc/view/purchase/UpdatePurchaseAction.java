package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseAction Ω√¿€");
		String tranNo = request.getParameter("tranNo");
		
		PurchaseService service = new PurchaseServiceImpl();
		Purchase purchase = service.getPurchase(Integer.parseInt(tranNo));
		
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("divyDate"));
		
		service.updatePurcahse(purchase);
		
		request.setAttribute("purchase", purchase);
		
		
		System.out.println("================AddPurchaseAction ≥°");
		return "forward:/purchase/updatePurchase.jsp";
	}
}