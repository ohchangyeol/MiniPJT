package com.model2.mvc.web.product;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;


//==> 회원관리 RestController
@RestController 
//리턴을 해줄 때 json으로 변경후 리턴.
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;	
	
	public ProductRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping(value= "json/listProduct")
	public Map<String, Object> listProduct(@ModelAttribute("search") Search search) throws Exception{
		
		System.out.println("/product/listProduct : GET / POST");
		System.out.println("search : "+search);
		
		if(search.getCurrentPage() == 0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		Map<String , Object> returnMap = new HashMap<String, Object>();
		returnMap.put("resultPage", resultPage);
		returnMap.put("search", search);
		returnMap.put("list", map.get("list"));
		return returnMap;
	}
	
	@RequestMapping( value="json/getProduct/{prodNo}", method=RequestMethod.GET )
	public Product getUser( @PathVariable int prodNo ) throws Exception{
		
		System.out.println("/user/json/getProduct : GET");
		
		//Business Logic
		return productService.getProduct(prodNo);
	}
	
	@RequestMapping(value= "json/addProduct", method = RequestMethod.POST)
	public Product addUser( @RequestBody Product product ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		productService.addProduct(product);
		
		return productService.getProduct(productService.getProdNo());
	}
	
	@RequestMapping( value="json/updateProduct/{prodNo}", method=RequestMethod.GET )
	public Product updateUser( @PathVariable int prodNo ) throws Exception{

		System.out.println("/product/updateProduct : GET");

		System.out.println("prodNo = " +prodNo );
		//Business Logic
		return productService.getProduct(prodNo);
	}

	@RequestMapping( value="json/updateProduct", method=RequestMethod.POST )
	public Product updateUser1( @RequestBody Product product , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		System.out.println("바인딩 product = " + product);
		productService.updateProduct(product);
		System.out.println("업데이트 완료.");
		
		return productService.getProduct(product.getProdNo());
	}
}