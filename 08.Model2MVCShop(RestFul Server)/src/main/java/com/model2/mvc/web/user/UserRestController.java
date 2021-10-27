package com.model2.mvc.web.user;

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
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController 
//리턴을 해줄 때 json으로 변경후 리턴.
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;	
	
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{

		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		return dbUser;
	}
	
	///////////////////////////////////////////////////////////////////////////
	// 10-20 ====================================
	@RequestMapping( value="json/updateUser/{userId}", method=RequestMethod.GET )
	public User updateUser( @PathVariable String userId ) throws Exception{

		System.out.println("/user/updateUser : GET");

		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/updateUser", method=RequestMethod.POST )
	public User updateUser1( @RequestBody User user , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		System.out.println("바인딩 user = " + user);
		userService.updateUser(user);
		System.out.println("업데이트 완료.");
		System.out.println("user Id ="+user.getUserId());
		User dbUser = userService.getUser(user.getUserId());
		System.out.println("dbUser : " + dbUser);
		return dbUser;
	}
	
	//10-21 ====================================
	
	@RequestMapping( value="json/checkDuplication", method=RequestMethod.POST )
	public Map<String, Object> checkDuplication( @RequestBody String userId ) throws Exception{
		
		System.out.println("/user/checkDuplication : POST");
		//Business Logic
		
		System.out.println(userId);
		boolean result=userService.checkDuplication(userId);
		// Model 과 View 연결
		
//		model.addAttribute("result", new Boolean(result));
//		model.addAttribute("userId", userId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", new Boolean(result));
		map.put("userId", new String(userId));
		return map;
	}

	
	@RequestMapping(value= "json/listUser")
	public Map<String, Object> listUser(@ModelAttribute("search") Search search) throws Exception{
		
		System.out.println("/user/listUser : GET / POST");
		System.out.println("search : "+search);
		
		if(search.getCurrentPage() == 0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = userService.getUserList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		Map<String , Object> returnMap = new HashMap<String, Object>();
		returnMap.put("resultPage", resultPage);
		returnMap.put("search", search);
		returnMap.put("list", map.get("list"));
		return returnMap;
	}
	
	@RequestMapping(value= "json/addUser", method = RequestMethod.POST)
	public User addUser( @RequestBody User user ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		
		return user;
	}
}