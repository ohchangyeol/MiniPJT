package com.model2.mvc.view.product;

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


public class UpdateProductAction extends Action{

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		ProductVO vo = (ProductVO)session.getAttribute("vo");
		
		System.out.println("======UpdateProductViewAction :: "+vo);
		
		vo.setFileName(request.getParameter("fileName"));
		vo.setManuDate(request.getParameter("manuDate").replace("-", ""));
		vo.setPrice(Integer.parseInt(request.getParameter("price")));
		vo.setProdName(request.getParameter("prodName"));
		vo.setProdDetail(request.getParameter("prodDetail"));
		
		ProductService service=new ProductServiceImpl();
		service.updateProduct(vo);
		
		request.setAttribute("vo", vo);

		return "forward:/product/updateProduct.jsp";
	}
}