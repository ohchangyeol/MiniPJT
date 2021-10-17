package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================AddPurchaseViewAction 시작");
//		System.out.println(" prodNo = "+ request.getParameter("prodNo"));
		Search search=new Search();
		
		int currentPage=1;
		if(request.getParameter("currentPage") != null&& !("".equals(request.getParameter("currentPage")))){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}

		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		PurchaseService purchaseService=new PurchaseServiceImpl();
		Purchase purchaseVO =  purchaseService.getPurchase2(Integer.parseInt(request.getParameter("prodNo")));
		
//		System.out.println(":: = purchaseVO = " + purchaseVO );
		
		purchaseService.updateTranCode(purchaseVO);
		
		Map<String,Object> map = purchaseService.getSaleList(search);
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		request.setAttribute("resultPage", resultPage);
		System.out.println(":: = .java의 map = "+map);
		System.out.println("================AddPurchaseViewAction 끝");
		return "forward:/product/listProduct.jsp";
	}
}