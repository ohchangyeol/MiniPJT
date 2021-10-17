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


public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseAction Ω√¿€");
		String tranNo = request.getParameter("tranNo");
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO pruchase = service.getPurchase(Integer.parseInt(tranNo));
		
		pruchase.setPaymentOption(request.getParameter("paymentOption"));
		pruchase.setReceiverName(request.getParameter("receiverName"));
		pruchase.setReceiverPhone(request.getParameter("receiverPhone"));
		pruchase.setDivyAddr(request.getParameter("receiverAddr"));
		pruchase.setDivyRequest(request.getParameter("receiverRequest"));
		pruchase.setDivyDate(request.getParameter("divyDate"));
		
		service.updatePurcahse(pruchase);
		
		request.setAttribute("vo", pruchase);
		
		
		System.out.println("================AddPurchaseAction ≥°");
		return "forward:/purchase/updatePurchase.jsp";
	}
}