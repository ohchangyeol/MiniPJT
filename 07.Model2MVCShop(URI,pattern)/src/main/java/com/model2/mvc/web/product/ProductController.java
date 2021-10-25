package com.model2.mvc.web.product;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


//==> ȸ������ Controller
@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;	
	
	
	// constructor
	public ProductController(){
		System.out.println("@Controller :: "+this.getClass());
	}
	/*
	@RequestMapping("addProductView")
	public String addProductView() throws Exception {
		//layout�� �̹� jsp �� �����ϵ��� ������� ������ ���� ���� ���� �� �𸣰���. 
		System.out.println("addProductView");
		
		return "redirect:/product/addProductView.jsp";
	}
	*/
	
	//Add Mapping
	@RequestMapping(value="addProduct", method=RequestMethod.GET) 
	 public String addProduct() throws Exception {
		 
		 System.out.println("addProduct GET��� ����");
		 
		 return "redirect:/product/addProductView.jsp"; 
	 }
	 @RequestMapping(value="addProduct", method=RequestMethod.POST) 
	 public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file, HttpServletRequest req)  throws Exception {
		 
		 System.out.println("addProduct POST��� ����");
		 
	     
		 if(!file.getOriginalFilename().isEmpty()) {
			 String root_path = req.getSession().getServletContext().getRealPath("/");  
		     String attach_path = "images/uploadFiles/";
		     String filename = file.getOriginalFilename();
		     //System.out.println("==> root :: "+root_path + attach_path + filename);
		     
			 product.setFileName(file.getOriginalFilename());
			 file.transferTo(new File(root_path + attach_path + filename));
			 System.out.println("==> upload �Ϸ�.");
		 }
		 product.setManuDate(product.getManuDate().replace("-", ""));
////		 System.out.println("==> product = "+product);
////		 System.out.println(uploadPath);
////		 System.out.println(file.getOriginalFilename());
//		 
		 //Business Logic
		 productService.addProduct(product);
		 return "forward:/product/addProduct.jsp"; 
		 
	      
	 }
	 //List Mapping
	 @RequestMapping("listProduct") 
	 public String listProduct( @ModelAttribute("search") Search search ,  Model model , HttpServletRequest request) throws Exception{
	 
		 System.out.println("/listProduct");
		 
		 if(search.getCurrentPage() == 0 ){ 
			 search.setCurrentPage(1); 
		 }
		 search.setPageSize(pageSize);
		 
		 // Business logic ���� 
		 Map<String , Object> map=productService.getProductList(search);
		 
		 Page resultPage = new Page( search.getCurrentPage() , ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		 
		 System.out.println(resultPage);
		 
		 // Model �� View ���� 
		 model.addAttribute("list", map.get("list"));
		 model.addAttribute("resultPage", resultPage); 
		 model.addAttribute("search", search);
		 
		 return "forward:/product/listProduct.jsp"; 
	 }
	 // GetProduct Mapping
	 @RequestMapping(value="getProduct", method=RequestMethod.GET) 
	 public String getProduct( @RequestParam("prodNo") int prodNo , @CookieValue(value = "history", required = false)Cookie cookie, HttpServletResponse res,Model model ) throws Exception {
		 System.out.println("getProduct get ��� ����");
		 String history = "";
		 
		 //cookie
		 if(cookie == null) {
			 cookie = new Cookie("history", history);
			 res.addCookie(cookie);
		 }
		 history+=cookie.getValue()+","+prodNo;
		 cookie = new Cookie("history", history);
		 res.addCookie(cookie);
		 
		 //Business Logic 
		 Product product = productService.getProduct(prodNo); 
		 // Model �� View ���� 
		 model.addAttribute("product",product);
	 
		 return "forward:/product/getProduct.jsp"; 
	 }
	 
	 //Update Mapping
	 //@RequestMapping("/updateProductView.do") 
	 @RequestMapping(value="updateProduct",method=RequestMethod.GET) 
	 public String updateUser( @RequestParam("prodNo") int prodNo , Model model ) throws Exception{
		 
		 System.out.println("updateProduct get ��� ����"); 
		 //Business Logic 
		 Product product = productService.getProduct(prodNo); 
		 
		 // Model �� View ���� 
		 model.addAttribute("product",product);
		 
		 return "forward:/product/updateProductView.jsp"; 
	 }
	  
	 @RequestMapping(value="updateProduct",method=RequestMethod.POST) 
	 public String updateUser( @ModelAttribute("product") Product product , Model model) throws Exception{
	 
		 System.out.println("updateProduct POST ��� ���� => ��ǰ ���� Ȯ��"); 

		 //Business Logic
		 productService.updateProduct(product);
		 Product returnProduct = productService.getProduct(product.getProdNo());
		 
		 // Model �� View ���� 
		 model.addAttribute("product", returnProduct);
		 
		 return "forward:/product/updateProduct.jsp"; 
	 }
}