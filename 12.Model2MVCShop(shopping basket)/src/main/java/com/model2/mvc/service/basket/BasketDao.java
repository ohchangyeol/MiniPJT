package com.model2.mvc.service.basket;

import java.util.List;

import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.Product;

public interface BasketDao {
	
	public int addBasket(Basket basket) throws Exception;

	public int deleteBasket(Basket basket) throws Exception;

	public List<Product> getBasketList(String buyerId) throws Exception;
	
}