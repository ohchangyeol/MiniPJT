package com.model2.mvc.web.basket;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.BuyBasket;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@RestController
@RequestMapping("/basket/*")
public class BasketRestController {
	
	///Field
	@Autowired
	@Qualifier("basketServiceImpl")
	private BasketService basketService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//Constructor
	public BasketRestController(){
		System.out.println(this.getClass());
	}
	 
	@PostMapping(value="json/deleteBasket") 
	public int deleteBasket(@RequestParam(value="prodList[]") List<String> prodList, 
	            			@RequestParam(value="user") String user) throws Exception {
		 
		System.out.println("rest deleteBasket 접근");
		 
		Basket basket = new Basket();
		basket.setBuyerId(user);
		int count = 0;	
		 
		System.out.println(prodList);
		System.out.println(user);
		 
		for (String prodNo : prodList) {
			basket.setProdNo(Integer.parseInt(prodNo));
			basketService.deleteBasket(basket);
			count++;
		}
		return count; 
	}
	@PostMapping(value="json/deleteAllBasket") 
	public int deleteAllBasket(@RequestBody Basket basket) throws Exception {
		 
		System.out.println("rest delete All Basket start");
		System.out.println(basket);
		return basketService.deleteBasket(basket);
	}
	 
	@GetMapping (value = "json/addBasket/{prodNo}")
	public int addBasket(@PathVariable("prodNo") int prodNo, HttpSession httpSession)  throws Exception {
		System.out.println("add Basket start ...");
		 
		User user = (User)httpSession.getAttribute("user");
		 
		System.out.println("----------\n Is the user NULL \n"+ user);
		System.out.println("pordNo = "+ prodNo);
		 
		if(user != null) { // 세션이 아직 존재 한다면
			Product product = productService.getProduct(prodNo); 
			System.out.println("재고 : " + product.getProdCount());

			if(product.getProdCount() != 0 ) { // 재고가 있다면
				Basket basket = new Basket(prodNo, user.getUserId());
				return basketService.addBasket(basket);
			}else return 400;
			 
		}else return 100;
		 
	}
	 
	@PostMapping(value="json/addBuyBasket")
	public int addBuyBasket(@RequestBody BuyBasket buyBasket, HttpSession httpSession) throws Exception{
		System.out.println("========\n addBuyBasket start");
//		System.out.println( "전달 받은 인자 값 \n" + buyBasket);
		
		buyBasket.getPurchase().setBuyer((User)httpSession.getAttribute("user"));
		
//		System.out.println("user setting  \n" + buyBasket );
		int count = 0 ;
		for (String prodNo : buyBasket.getProdNo()) {
			
			Product product = productService.getProduct(Integer.parseInt(prodNo));
			
			product.setProdCount( product.getProdCount() - Integer.parseInt( buyBasket.getProdCount().get(count)) );
			
			System.out.println(product);
			
			productService.updateProductCount(product);
			
			count++;
		}
		Basket basket = new Basket();
		basket.setBuyerId(buyBasket.getPurchase().getBuyer().getUserId());
		basketService.deleteBasket(basket);
		
		return basketService.addBasketList(buyBasket);
	}
	
}