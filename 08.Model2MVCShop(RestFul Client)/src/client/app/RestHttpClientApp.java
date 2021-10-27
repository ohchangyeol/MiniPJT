package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Page;
import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
		//RestHttpClientApp.getUserTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 2.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.LoginTest_Codehaus();
		
		//10-20////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		//3.1 Http Protocol GET Request : JsonSimple lib 사용
//		RestHttpClientApp.updateTest_JsonSimple();		
		
//		System.out.println("\n====================================\n");
//		//3.1 Http Protocol GET Request : codehaus lib 사용
//		RestHttpClientApp.updateTest_Codehaus();		
		
//		System.out.println("\n====================================\n");
//		RestHttpClientApp.updatePostTest_Codehaus();
		
		//10-21////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		RestHttpClientApp.checkDuplication_Codehaus();
		
//		System.out.println("\n====================================\n");
//		RestHttpClientApp.listUserGet_Codehaus();
		
//		System.out.println("\n====================================\n");
//		RestHttpClientApp.listUserPost_Codehaus();

		System.out.println("\n====================================\n");
		RestHttpClientApp.addUserPost_Codehaus();
		
	}
	
	
//================================================================//
	//1.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}

//================================================================//	
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:8080/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
//		System.out.println("Object ==> jsonValue로 변환"+jsonValue);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
//		System.out.println("==> httpEntity01 :: "+httpEntity01);
		
		httpPost.setEntity(httpEntity01);
		
//		System.out.println("httpPost :: "+httpPost.getEntity());
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	

//==================10-20==============================================//
	//3.1 Http Protocol GET Request : JsonSimple 3rd party lib 사용
	public static void updateTest_JsonSimple() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/updateUser/user01";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//3.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void updateTest_Codehaus() throws Exception{
		
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/user/json/updateUser/user01";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
		
		//==> 내용읽기(JSON Value 확인)
//		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
//		System.out.println(jsonobj);
	}
	//4.1
	public static void updatePostTest_Codehaus() throws Exception{
		
		System.out.println("updatePostTest_Codehaus start...");
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/updateUser";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		User user = new User();
		user.setUserId("user01");
		user.setPassword("1111");
		user.setUserName("유저01");
		user.setAddr("경기도 양주");
		user.setPhone("111-2222-3333");
		
		System.out.println("전송할 user 정보 = "+user);
		ObjectMapper om = new ObjectMapper();
		
		String jsonValue = om.writeValueAsString(user);
		
		//System.out.println("==> 바디에 담을 정보 :: "+jsonValue +"\n");
		
		HttpEntity entity = new StringEntity(jsonValue,"utf-8");
		
		//System.out.println(entity);
		httpPost.setEntity(entity);
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
//		
//		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
////		String serverData = br.readLine();
////		System.out.println(serverData);
//		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		//ObjectMapper objectMapper = new ObjectMapper();
		user = om.readValue(jsonobj.toString(), User.class);
		System.out.println(user);
		
	}
//==================10-21==============================================//
	public static void checkDuplication_Codehaus() throws Exception{
		
		System.out.println("updatePostTest_Codehaus start...");
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/checkDuplication";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		ObjectMapper om = new ObjectMapper();
		
//		String exString = "user01";
		
//		String jsonValue = om.writeValueAsString("user01");
		
//		System.out.println(jsonValue);
		
		//System.out.println("==> 바디에 담을 정보 :: "+jsonValue +"\n");
		
		HttpEntity entity = new StringEntity("user01","utf-8");
		
		//System.out.println(entity);
		httpPost.setEntity(entity);
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
//		
//		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
////		String serverData = br.readLine();
////		System.out.println(serverData);
//		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
//		user = om.readValue(jsonobj.toString(), User.class);
//		System.out.println(user);
		
	}
	
	public static void listUserGet_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/listUser";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
//		String serverData = br.readLine();
//		System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
//		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
//		 System.out.println(user);
		Map<String, Object> map = objectMapper.readValue(jsonobj.toString(), Map.class);
		
		System.out.println("map \n\n"+ map);
		
	}
	
	public static void listUserPost_Codehaus() throws Exception{
		
		System.out.println("updatePostTest_Codehaus start...");
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/listUser";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		Search search = new Search();
		search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");

	 	ObjectMapper om = new ObjectMapper();
		
		String jsonValue = om.writeValueAsString(search);
		
		//System.out.println("==> 바디에 담을 정보 :: "+jsonValue +"\n");
		
		HttpEntity entity = new StringEntity(jsonValue,"utf-8");
		
		//System.out.println(entity);
		httpPost.setEntity(entity);
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
//		
//		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
////		String serverData = br.readLine();
////		System.out.println(serverData);
//		
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj.get("list"));
		System.out.println(jsonobj.get("resultPage"));
		System.out.println(jsonobj.get("search"));
	
		
		//ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = om.readValue(jsonobj.toString(), Map.class);
//		List<User> users = om.readValue(jsonobj.toString(), List.class);
//		Page page = om.readValue(jsonobj.toString(), Page.class);
		
		System.out.println("map \n\n"+ map);
//		System.out.println(page);
//		System.out.println(users);
	}
	
	public static void addUserPost_Codehaus() throws Exception{
		
		System.out.println("updatePostTest_Codehaus start...");
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/user/json/addUser";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		User user = new User();
		user.setUserId("testUserId");
		user.setUserName("testUserName");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("경기도");
		user.setEmail("test@test.com");
		
	 	ObjectMapper om = new ObjectMapper();
		
		String jsonValue = om.writeValueAsString(user);
		
		HttpEntity entity = new StringEntity(jsonValue,"utf-8");
		httpPost.setEntity(entity);
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
//		
//		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		
		//ObjectMapper objectMapper = new ObjectMapper();
		user = om.readValue(jsonobj.toString(), User.class);
		
		System.out.println(user);
	}
}