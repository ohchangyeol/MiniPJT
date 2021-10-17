package com.model2.mvc.view.product;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;


public class GetProductAction extends Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("///GetUserAction Start");
		//history
		Cookie[] cookies = request.getCookies();
		String history ="";
		String prodNo = request.getParameter("prodNo");
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					history += cookie.getValue();
				}
			}
		}
		history += prodNo+ ",";
		
		Cookie c = new Cookie("history", history) ;
		response.addCookie(c);
		
		// getProduct 
		ProductService userService = new ProductServiceImpl();
		Product product = userService.getProduct(Integer.parseInt(prodNo));
		
		request.setAttribute("product", product);
		
		return "forward:/product/getProduct.jsp";
	}
}

