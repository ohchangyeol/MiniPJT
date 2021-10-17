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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


//==> 회원관리 Controller
@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public PurchaseController(){
		System.out.println("@Controller :: "+this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public String addProductView(@RequestParam("prod_no") int prodNo, Model model) throws Exception {
		System.out.println("/addProductView.do");
		
		Product product = productService.getProduct(prodNo);
		
		model.addAttribute("product", product);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	
	 @RequestMapping("/addPurchase.do") 
	 public String addPurchase( @ModelAttribute("product") Product product ,
			 					@ModelAttribute("purchase") Purchase purchase,
			 					HttpSession session
			 					) throws Exception {
		 
		 
		 System.out.println("/addPurchase.do"); 
		 
		 purchase.setPurchaseProd(product);
		 purchase.setBuyer((User)session.getAttribute("user"));
		 purchase.setTranCode("1");
		 
		 System.out.println("user :: " + session.getAttribute("user"));

		 //Business Logic
		 purchaseService.addPurchase(purchase);
		 
		 return "forward:/purchase/addPurchase.jsp"; 
	 }
	 
	 @RequestMapping("/listPurchase.do") 
	 public String listPurchase( @ModelAttribute("search") Search search , Model model , HttpSession session) throws Exception{
	 
		 System.out.println("/listPurchase.do");
		 
		 if(search.getCurrentPage() ==0 ){ 
			 search.setCurrentPage(1); 
		 }
		 search.setPageSize(pageSize);
		 Map<String,Object> searchMap = new HashMap<String, Object>();
		 searchMap.put("search", search);
		 searchMap.put("user", session.getAttribute("user") );
		 
		 // Business logic 수행 
		 Map<String , Object> map=purchaseService.getPurchaseList(searchMap);
		 
		 Page resultPage = new Page( search.getCurrentPage() , ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 
		 System.out.println(resultPage);
		 
		 // Model 과 View 연결 
		 model.addAttribute("list", map.get("list"));
		 model.addAttribute("resultPage", resultPage); 
		 model.addAttribute("search", search);
		 
		 return "forward:/purchase/listPurchase.jsp"; 
	 }
	 
	 @RequestMapping("/getPurchase.do") 
	 public String getPurchase( @RequestParam("tranNo") int tranNo , Model model ) throws Exception {
		 System.out.println("/getpurchase.do"); 
		 
		 //Business Logic 
		 Purchase purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 model.addAttribute("purchase",purchase);
	 
		 return "forward:/purchase/getPurchase.jsp"; 
	 }//updatePurchaseView.do
	 
	 @RequestMapping("/updatePurchaseView.do") 
	 public String updatePurchaseView( @RequestParam("tranNo") int tranNo , Model model ) throws Exception {
		 System.out.println("/getpurchase.do"); 
		 
		 //Business Logic 
		 Purchase purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 model.addAttribute("purchase",purchase);
	 
		 return "forward:/purchase/updatePurchaseView.jsp"; 
	 }
	 
	 @RequestMapping("/updatePurchase.do") 
	 public String updatePurchase( @RequestParam("tranNo") int tranNo , 
			 						@ModelAttribute("purchase") Purchase purchase,
			 						Model model ) throws Exception {
		 System.out.println("/updatePurchase.do"); 
		 
		 System.out.println("tranNo :: "+tranNo);
		 purchase.setTranNo(tranNo);
		 
		 System.out.println("purchase :: "+ purchase);
		 purchaseService.updatePurcahse(purchase);
		 //Business Logic 
		 purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 model.addAttribute("purchase",purchase);
	 
		 return "forward:/purchase/updatePurchase.jsp"; 
	 }
	 
	 @RequestMapping("/updateTranCodeByProd.do") 
	 public String updateTranCodeByProd( @RequestParam("prodNo") int prodNo , 
			 						@ModelAttribute("search") Search search, 
			 						Model model ) throws Exception {
		 System.out.println("/updatePurchase.do"); 

		 // tranCode Update part
		 System.out.println("==> prodNo :: "+prodNo);
		 Purchase purchase = purchaseService.getPurchase2(prodNo);

		 int tranCode = Integer.parseInt(purchase.getTranCode().trim())+1;
		 purchase.setTranCode(tranCode+"");
		 System.out.println("==>purchase :: "+ purchase);
		 purchaseService.updateTranCode(purchase);

		 
		 // return List Product part
		 if(search.getCurrentPage() ==0 ){ 
			 search.setCurrentPage(1); 
		 }
		 search.setPageSize(pageSize);
		 
		 
		 Map<String , Object> map = productService.getProductList(search);
		 
		 Page resultPage = new Page( search.getCurrentPage() , ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 
		 System.out.println(resultPage);

		 
		 //Business Logic 
				 
		 // Model 과 View 연결 
		 model.addAttribute("list", map.get("list"));
		 model.addAttribute("resultPage", resultPage); 
		 model.addAttribute("search", search);
		 
		 return "forward:/product/listProduct.jsp";
	 }//updateTranCode.do?prodNo=10054
	 
	 @RequestMapping("/updateTranCode.do") 
	 public String updateTranCode( @RequestParam("prodNo") int prodNo , 
			 						@ModelAttribute("search") Search search, 
			 						Model model,
			 						HttpSession session) throws Exception {
		 System.out.println("/updatePurchase.do"); 

		 // tranCode Update part
		 System.out.println("==> prodNo :: "+prodNo);
		 Purchase purchase = purchaseService.getPurchase2(prodNo);

		 int tranCode = Integer.parseInt(purchase.getTranCode().trim())+1;
		 purchase.setTranCode(tranCode+"");
		 System.out.println("==>purchase :: "+ purchase);
		 purchaseService.updateTranCode(purchase);

		 
		 // return List purchase part
		 if(search.getCurrentPage() ==0 ){ 
			 search.setCurrentPage(1); 
		 }
		 search.setPageSize(pageSize);
		 Map<String,Object> searchMap = new HashMap<String, Object>();
		 searchMap.put("search", search);
		 searchMap.put("user", session.getAttribute("user") );
		 
		 // Business logic 수행 
		 Map<String , Object> map=purchaseService.getPurchaseList(searchMap);
		 
		 Page resultPage = new Page( search.getCurrentPage() , ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 
		 System.out.println(resultPage);
		 
		 // Model 과 View 연결 
		 model.addAttribute("list", map.get("list"));
		 model.addAttribute("resultPage", resultPage); 
		 model.addAttribute("search", search);
		 
	 
		 return "forward:/purchase/listPurchase.jsp"; 
	 }
}