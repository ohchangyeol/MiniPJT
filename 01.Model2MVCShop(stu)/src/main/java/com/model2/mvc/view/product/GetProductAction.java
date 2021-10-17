package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;


public class GetProductAction extends Action{
	
	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		Cookie[] cookies = request.getCookies();
		String history ="";
		
		if (cookies!=null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("history")) {
					history += cookie.getValue();
				}
			}
		}
		history += request.getParameter("prodNo")+ ",";
		
		Cookie c = new Cookie("history", history) ;
		response.addCookie(c);
		
		System.out.println("**********2*********");
		System.out.println(history);
//		System.out.println("*******************");
		ProductService service=new ProductServiceImpl();
		ProductVO vo = service.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		
		request.setAttribute("vo", vo);
		
		return "forward:/product/getProduct.jsp";
	}
}