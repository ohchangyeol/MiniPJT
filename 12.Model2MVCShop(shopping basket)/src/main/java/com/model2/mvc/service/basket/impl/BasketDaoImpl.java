package com.model2.mvc.service.basket.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.service.basket.BasketDao;
import com.model2.mvc.service.domain.Basket;
import com.model2.mvc.service.domain.BuyBasket;
import com.model2.mvc.service.domain.Product;


//==> 회원관리 DAO CRUD 구현
@Repository("basketDaoImpl")
public class BasketDaoImpl implements BasketDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	//setter
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public BasketDaoImpl() {
		System.out.println("@Repository :: "+this.getClass());
	}

	@Override
	public int addBasket(Basket basket) throws Exception {
		return sqlSession.insert("BasketMapper.addBasket", basket);
	}

	@Override
	public int deleteBasket(Basket basket) throws Exception {
		return sqlSession.delete("BasketMapper.deleteBasket", basket);
	}

	@Override
	public List<Product> getBasketList(String buyerId) throws Exception {
		
		return sqlSession.selectList("BasketMapper.getBasketList",buyerId);
	}
	
	
	@Override
	public int addBasketList(BuyBasket buyBasket) throws Exception {
		return sqlSession.insert("PurchaseMapper.addBasketList", buyBasket);
	}
	
	
}