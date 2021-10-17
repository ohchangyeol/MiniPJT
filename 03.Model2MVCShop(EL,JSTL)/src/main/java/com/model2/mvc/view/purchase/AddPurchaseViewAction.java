package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseViewAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseViewAction Ω√¿€");
		System.out.println(" prodNo = "+ request.getParameter("prod_no"));
		
		HttpSession session=request.getSession();
		ProductService productService=new ProductServiceImpl();
		Product productVO =  productService.getProduct(Integer.parseInt(request.getParameter("prod_no")));

		Purchase vo = new Purchase();
		vo.setBuyer((User)session.getAttribute("user"));
		vo.setPurchaseProd(productVO);
		
		request.setAttribute("purchase", vo);
		
		System.out.println("================AddPurchaseViewAction ≥°");
		return "forward:/purchase/addPurchaseView.jsp";
	}
}