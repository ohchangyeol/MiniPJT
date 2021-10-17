package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class UpdatePurchaseViewAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println(":: UpdatePurchaseViewAction Start ");
		String tranNo = request.getParameter("tranNo");
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO pruchase = service.getPurchase(Integer.parseInt(tranNo));
		
		request.setAttribute("pruchase", pruchase);
		System.out.println("====:: "+pruchase);
		System.out.println(":: UpdatePurchaseViewAction End ");
		return "forward:/purchase/updatePurchaseView.jsp";
	}
}