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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;


//==> 회원관리 Controller
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {
	
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
	public PurchaseController(){
		System.out.println("@Controller :: "+this.getClass());
	}
	
	@RequestMapping(value="addPurchase", method=RequestMethod.GET)
//	public ModelAndView addPurchaseView(@RequestParam("prod_no") int prodNo, Model model) throws Exception {
	public ModelAndView addPurchaseView(@RequestParam("prod_no") int prodNo) throws Exception {
		System.out.println("addPurchase GET 방식 접근");
		
		//Business Logic
		Product product = productService.getProduct(prodNo);
		
		//Model And View 연결
		//model.addAttribute("product", product);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/purchase/addPurchaseView.jsp");
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	
	 @RequestMapping(value="addPurchase", method=RequestMethod.POST) 
	 public ModelAndView addPurchase( @ModelAttribute("product") Product product ,
			 					@ModelAttribute("purchase") Purchase purchase,
			 					HttpSession session
			 					) throws Exception {
		 
		 System.out.println("addPurchase POST 접근 방식 ==> 구매 페이지 연결"); 
		 
		 
		 purchase.setPurchaseProd(product);
		 purchase.setBuyer((User)session.getAttribute("user"));
		 purchase.setTranCode("1");
		 
		 purchase.setTotalPrice(product.getPrice()*purchase.getTranCount());
		 //System.out.println("user :: " + session.getAttribute("user"));
		 System.out.println("== 수량 바꾸기 전 ==");
		 System.out.println(product);
		 
		 product.setProdCount(product.getProdCount() -  purchase.getTranCount());
		 System.out.println("== 수량 바꾼 후 ==");
		 System.out.println(product);
		 
		 
		 //Business Logic
		 purchaseService.addPurchase(purchase, product);
		 
		 //Model And View 연결
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("forward:/purchase/addPurchase.jsp");
		 return modelAndView; 
	 }
	 
	 @RequestMapping("listPurchase") 
//	 public ModelAndView listPurchase( @ModelAttribute("search") Search search , Model model , HttpSession session) throws Exception{
	 public ModelAndView listPurchase( @ModelAttribute("search") Search search , HttpSession session) throws Exception{
	 
		 System.out.println("listPurchase get방식 접근");
		 
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

		 
		 ModelAndView modelAndView = new ModelAndView();
		 modelAndView.setViewName("forward:/purchase/listPurchase.jsp");
		 modelAndView.addObject("list", map.get("list"));
		 modelAndView.addObject("resultPage", resultPage);
		 modelAndView.addObject("search", search);
		 
		 return modelAndView; 
	 }
	 
	 @RequestMapping(value="getPurchase",method=RequestMethod.GET) 
	 public ModelAndView getPurchase( @RequestParam("tranNo") int tranNo) throws Exception {
		 System.out.println("/getpurchase.do"); 
		 
		 //Business Logic 
		 Purchase purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 //model.addAttribute("purchase",purchase);
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("forward:/purchase/getPurchase.jsp");
		 mv.addObject("purchase",purchase);
		 
		 //return "forward:/purchase/getPurchase.jsp"; 
		 return mv; 
	 }//updatePurchaseView.do
	 
	 //@RequestMapping("/updatePurchaseView.do") 
	 @RequestMapping(value= "updatePurchase",method=RequestMethod.GET)
	 //public String updatePurchaseView( @RequestParam("tranNo") int tranNo , Model model ) throws Exception {
	 public ModelAndView updatePurchase( @RequestParam("tranNo") int tranNo) throws Exception {
		 System.out.println("updatePurchase get 방식 접근 = view 연결"); 
		 
		 //Business Logic 
		 Purchase purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 //model.addAttribute("purchase",purchase);
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("forward:/purchase/updatePurchaseView.jsp");
		 mv.addObject("purchase",purchase);
		 //return "forward:/purchase/updatePurchaseView.jsp"; 
		 return mv; 
	 }
	 
	 //@RequestMapping("/updatePurchase.do")
	 @RequestMapping(value="updatePurchase",method=RequestMethod.POST)
	 //public String updatePurchase( @RequestParam("tranNo") int tranNo , 
	 public ModelAndView updatePurchase( @RequestParam("tranNo") int tranNo , 
			 							 @ModelAttribute("purchase") Purchase purchase) throws Exception {
		 
		 System.out.println("/updatePurchase.do"); 
		 
		 //System.out.println("tranNo :: "+tranNo);
		 purchase.setTranNo(tranNo);
		 
		 //System.out.println("purchase :: "+ purchase);
		 purchaseService.updatePurcahse(purchase);
		 //Business Logic 
		 purchase = purchaseService.getPurchase(tranNo);
		 purchase.setPaymentOption(purchase.getPaymentOption().trim());
		 //System.out.println("purchase :: "+ purchase);
		 
		 // Model 과 View 연결 
		 //model.addAttribute("purchase",purchase);
		 
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("forward:/purchase/updatePurchase.jsp");
		 mv.addObject("purchase",purchase);
		 
		 //return "forward:/purchase/updatePurchase.jsp"; 
		 return mv; 
	 }
	 
	 @RequestMapping(value="updateTranCodeByProd",method=RequestMethod.GET) 
	 public ModelAndView updateTranCodeByProd( @RequestParam("prodNo") int prodNo , 
			 						@ModelAttribute("search") Search search) throws Exception {
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
		 
		 //Business Logic 
		 Map<String , Object> map = productService.getProductList(search);
		 Page resultPage = new Page( search.getCurrentPage() , ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 System.out.println(resultPage);

				 
		 // Model 과 View 연결 
//		 model.addAttribute("list", map.get("list"));
//		 model.addAttribute("resultPage", resultPage); 
//		 model.addAttribute("search", search);
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("forward:/product/listProduct.jsp");
		 mv.addObject("list",map.get("list"));
		 mv.addObject("resultPage", resultPage);
		 mv.addObject("search", search);
		 
		 //return "forward:/product/listProduct.jsp";
		 return mv;
	 }//updateTranCode.do?prodNo=10054
	 
	 @RequestMapping(value="updateTranCode", method=RequestMethod.GET) 
	 public ModelAndView updateTranCode( @RequestParam("prodNo") int prodNo , 
			 						@ModelAttribute("search") Search search, 
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
//		 model.addAttribute("list", map.get("list"));
//		 model.addAttribute("resultPage", resultPage); 
//		 model.addAttribute("search", search);
		 ModelAndView mv = new ModelAndView();
		 mv.setViewName("forward:/purchase/listPurchase.jsp");
		 mv.addObject("list",map.get("list"));
		 mv.addObject("resultPage", resultPage);
		 mv.addObject("search", search);
	 
//		 return "forward:/purchase/listPurchase.jsp"; 
		 return mv; 
	 }
}