package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseViewAction Ω√¿€");
		System.out.println(" prodNo = "+ request.getParameter("prod_no"));
		
		HttpSession session=request.getSession();
		ProductService productService=new ProductServiceImpl();
		ProductVO productVO =  productService.getProduct(Integer.parseInt(request.getParameter("prod_no")));

		PurchaseVO vo = new PurchaseVO();
		vo.setBuyer((UserVO)session.getAttribute("user"));
		vo.setPurchaseProd(productVO);
		
		request.setAttribute("purchase", vo);
		
		System.out.println("================AddPurchaseViewAction ≥°");
		return "forward:/purchase/addPurchaseView.jsp";
	}
}