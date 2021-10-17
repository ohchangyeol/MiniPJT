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
		Purchase pruchase = service.getPurchase(Integer.parseInt(tranNo));
		
		pruchase.setPaymentOption(request.getParameter("paymentOption"));
		pruchase.setReceiverName(request.getParameter("receiverName"));
		pruchase.setReceiverPhone(request.getParameter("receiverPhone"));
		pruchase.setDivyAddr(request.getParameter("receiverAddr"));
		pruchase.setDivyRequest(request.getParameter("receiverRequest"));
		pruchase.setDivyDate(request.getParameter("divyDate"));
		
		service.updatePurcahse(pruchase);
		
		request.setAttribute("pruchase", pruchase);
		
		
		System.out.println("================AddPurchaseAction ≥°");
		return "forward:/purchase/updatePurchase.jsp";
	}
}