package com.model2.mvc.web.purchase;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;

@RestController
@RequestMapping("/purchase/*")
public class PurchaseRestController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	//Constructor
	public PurchaseRestController(){
		System.out.println(this.getClass());
	}
	 
	 @RequestMapping(value="json/getPurchase/{tranNo}",method=RequestMethod.GET) 
	 public Purchase getPurchase( @PathVariable("tranNo") int tranNo) throws Exception {
		 System.out.println("/json/getPurchase GET 방식 접근"); 
		 
		 System.out.println("tranNo : "+ tranNo);
		 //Business Logic 
		 Purchase purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 purchase.setTranCode(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 return purchase; 
	 }
	 
	 @RequestMapping( value="json/addPurchase/{prodNo}", method=RequestMethod.GET )
		public Product addPurchase( @PathVariable int prodNo ) throws Exception{
			//구매를 해야 할 상품을 Select 하고 화면에 출력 하기 위한 용도의 method.
			System.out.println("/purchase/json/getPurchase : GET");
			
			//Business Logic
			return productService.getProduct(prodNo);
		}
	 
}