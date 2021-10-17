package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(	HttpServletRequest request,
												HttpServletResponse response) throws Exception {
		System.out.println("================ListPurchaseAction Ω√¿€");

		HttpSession session = request.getSession();
		
		Search search=new Search();
		String userId = (String)((User) session.getAttribute("user")).getUserId();
		
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
		
		PurchaseService service = new PurchaseServiceImpl();
//		service.getPurchaseList(searchVO,userId);
		Map<String,Object> map = service.getPurchaseList(search,userId);
		Page resultPage	= 
				new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("= map "+map);
		
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("searchVO", search);
		
		System.out.println("================ListPurchaseAction ≥°");
		return "forward:/purchase/listPurchase.jsp";
	}
}