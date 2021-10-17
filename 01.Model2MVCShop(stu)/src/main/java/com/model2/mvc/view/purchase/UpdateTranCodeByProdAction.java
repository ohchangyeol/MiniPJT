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
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;


public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseViewAction 시작");
//		System.out.println(" prodNo = "+ request.getParameter("prodNo"));
		String pageUnit=getServletContext().getInitParameter("pageSize");
		SearchVO searchVO=new SearchVO();
		
		int page=1;
		if(request.getParameter("page") != null) {
			page=Integer.parseInt(request.getParameter("page"));
		}

		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		
		PurchaseService purchaseService=new PurchaseServiceImpl();
		PurchaseVO purchaseVO =  purchaseService.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
		
//		System.out.println(":: = purchaseVO = " + purchaseVO );
		
		purchaseService.updateTranCode(purchaseVO);
		
		HashMap<String,Object> map = purchaseService.getSaleList(searchVO);
		
		
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		System.out.println(":: = .java의 map = "+map);
		System.out.println("================AddPurchaseViewAction 끝");
		return "forward:/product/listProduct.jsp";
	}
}