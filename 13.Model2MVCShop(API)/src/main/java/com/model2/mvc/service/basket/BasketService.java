package com.model2.mvc.service.basket;

import java.util.List;

import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.BuyBasket;
import com.model2.mvc.service.domain.Product;

public interface BasketService {
	
	public int addBasket(Basket basket) throws Exception;

	public int deleteBasket(Basket basket) throws Exception;

	public List<Product> getBasketList(String string) throws Exception;

	public int addBasketList(BuyBasket buyBasket) throws Exception;
}