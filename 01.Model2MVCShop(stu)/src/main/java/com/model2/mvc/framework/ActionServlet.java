package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	private RequestMapping mapper;

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("==============================Start init metgod============================");
		
		String resources=getServletConfig().getInitParameter("resources");
		System.out.println("¸®¼Ò½º = "+resources);
		
		mapper=RequestMapping.getInstance(resources);
		System.out.println("¸ÊÆÛ = "+mapper);
		
		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		System.out.println("==============================Start Service metgod============================");
		String url = request.getRequestURI();
//		System.out.println("****ActionServlet getRequestURI()****");
		System.out.println("URI = \t"+url);
		
		String contextPath = request.getContextPath();
//		System.out.println("****ActionServlet getContextPath()****");
		System.out.println("contextPath = \t"+contextPath);
		
		String path = url.substring(contextPath.length());
//		System.out.println("****ActionServlet path****");
		System.out.println("path = \t"+path);
		
		try{
			Action action = mapper.getAction(path);
			action.setServletContext(getServletContext());
			System.out.println("==================================================");
			System.out.println(action);
			String resultPage=action.execute(request, response);
			System.out.println("==================================================");
			System.out.println(resultPage);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			System.out.println("==================================================");
			System.out.println( "result \t = " + result);
			if(resultPage.startsWith("forward:")) {
				HttpUtil.forward(request, response, result);
			}else {
				HttpUtil.redirect(response, result);
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}