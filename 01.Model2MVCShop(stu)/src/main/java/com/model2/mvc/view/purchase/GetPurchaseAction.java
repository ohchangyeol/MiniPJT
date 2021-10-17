package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class GetPurchaseAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		String tranNo = request.getParameter("tranNo");
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO pruchase = service.getPurchase(Integer.parseInt(tranNo));
		
		request.setAttribute("vo", pruchase);

		return "forward:/purchase/getPurchase.jsp";
	}
}