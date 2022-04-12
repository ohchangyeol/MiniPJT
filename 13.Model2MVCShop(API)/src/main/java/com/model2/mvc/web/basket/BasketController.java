package com.model2.mvc.web.basket;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.jasper.compiler.JspRuntimeContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


//==> 雀盔包府 Controller
@Controller
@RequestMapping("/basket/*")
public class BasketController {
	
	///Field
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;

	//Constructor
	public BasketController(){
		System.out.println("@Controller :: "+this.getClass());
	}
	
	@RequestMapping(value="listBasket", method=RequestMethod.GET)
	public ModelAndView addPurchaseView(HttpSession httpSession) throws Exception {
		System.out.println("listBasket GET 规侥 立辟");
		
		User user = (User) httpSession.getAttribute("user");
//		System.out.println(user);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/basket/listBasket.jsp");
		modelAndView.addObject("list",  basketService.getBasketList(user.getUserId()));
		return modelAndView;
	}
	
	
}