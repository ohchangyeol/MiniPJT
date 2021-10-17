package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request,	HttpServletResponse response) throws Exception {

		Search search=new Search();
		
		int currentPage=1;

		System.out.println("= request.getParameter(\"currentPage\")�� ���� = " + request.getParameter("currentPage"));
		System.out.println(request.getParameter("searchCondition"));
		System.out.println("= request.getParameter(\"currentPage\")�� null�� �ƴ϶�� ture , null�̶�� false ----��� �� [" + (request.getParameter("currentPage") != null)+"]");
		System.out.println("= \" \"�� getSearchCondition()�� ��ġ���� �ʴٸ� = [" + !("".equals(request.getParameter("currentPage")))+"]");
			
		if(request.getParameter("currentPage") != null&& !("".equals(request.getParameter("currentPage")))){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}
		
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchKeyword(request.getParameter("searchKeyword"));
		
		// web.xml  meta-data �� ���� ��� ���� 
		int pageSize = Integer.parseInt( getServletContext().getInitParameter("pageSize"));
		int pageUnit  =  Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// Business logic ����
		ProductService service=new ProductServiceImpl();
		Map<String , Object> map=service.getProductList(search);

		System.out.println("================================");
		System.out.println("= pageSize = " + pageSize);
		System.out.println("= pageUnit = " + pageUnit);
		System.out.println("= currentPage = " + currentPage);
		System.out.println("= map = " + map);
		System.out.println("================================");
		Page resultPage	= 
					new Page( currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListUserAction ::"+resultPage);
		
		// Model �� View ����
		request.setAttribute("list", map.get("list"));
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("search", search);
		
		return "forward:/product/listProduct.jsp";
	}
}