package com.model2.mvc.service.basket.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.basket.BasketService;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Product;


//==> 회원관리 서비스 구현
@Service("basketServiceImpl")
public class BasketServiceImpl implements BasketService{
	
	///Field
	@Autowired
	@Qualifier("basketDaoImpl")
	private BasketDao basketDao;
	
	//setter
	public void setBasketDao(BasketDao basketDao) {
		this.basketDao = basketDao;
	}
	
	///Constructor
	public BasketServiceImpl() {
		System.out.println("@Service :: "+this.getClass());
	}

	@Override
	public int addBasket(Basket basket) throws Exception {
		return basketDao.addBasket(basket);
	}

	@Override
	public int deleteBasket(Basket basket) throws Exception {
		return basketDao.deleteBasket(basket);
	}

	@Override
	public List<Product> getBasketList(String buyerId) throws Exception {
		return basketDao.getBasketList(buyerId);
	}
	
	
	

}