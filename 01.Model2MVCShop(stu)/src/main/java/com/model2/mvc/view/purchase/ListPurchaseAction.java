package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================ListPurchaseAction Ω√¿€");
		SearchVO searchVO=new SearchVO();
		HttpSession session = request.getSession();
		String userId = (String)((UserVO) session.getAttribute("user")).getUserId();
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		PurchaseVO purchase = new PurchaseVO();
		
		PurchaseService service = new PurchaseServiceImpl();
//		service.getPurchaseList(searchVO,userId);
		HashMap<String,Object> map = service.getPurchaseList(searchVO,userId);
		
		System.out.println("= map "+map);
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		System.out.println("================ListPurchaseAction ≥°");
		return "forward:/purchase/listPruchase.jsp";
	}
}