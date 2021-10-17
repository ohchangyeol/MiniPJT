package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.dao.UserDAO;
import com.model2.mvc.service.user.vo.UserVO;


public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO=new ProductDAO();
	}

	@Override
	public void addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
	}

	@Override
	public ProductVO getProduct(int prodNo) throws Exception {
		ProductVO productVO = productDAO.getProduct(prodNo);
		return productVO;
	}

	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception {
		// TODO Auto-generated method stub
		productDAO.updateProduct(productVO);
	}

//	public void addUser(UserVO userVO) throws Exception {
//		userDAO.insertUser(userVO);
//	}
//
//	public UserVO loginUser(UserVO userVO) throws Exception {
//			UserVO dbUser=userDAO.findUser(userVO.getUserId());
//
//			if(! dbUser.getPassword().equals(userVO.getPassword()))
//				throw new Exception("로그인에 실패했습니다.");
//			
//			return dbUser;
//	}
//
//	public UserVO getUser(String userId) throws Exception {
//		return userDAO.findUser(userId);
//	}
//
//	public HashMap<String,Object> getUserList(SearchVO searchVO) throws Exception {
//		return userDAO.getUserList(searchVO);
//	}
//
//	public void updateUser(UserVO userVO) throws Exception {
//		userDAO.updateUser(userVO);
//	}
//
//	public boolean checkDuplication(String userId) throws Exception {
//		boolean result=true;
//		UserVO userVO=userDAO.findUser(userId);
//		if(userVO != null) {
//			result=false;
//		}
//		return result;
//	}
}