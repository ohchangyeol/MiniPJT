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
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientProductApp {
	
	// Main Method
	public static void main(String[] args) throws Exception{
		
//		System.out.println("\n====================================\n");
		//RestHttpClientProductApp.listProductGet_Codehaus();
		
//		System.out.println("\n====================================\n");
		//RestHttpClientProductApp.listProductPost_Codehaus();
		
//		System.out.println("\n====================================\n");
		RestHttpClientProductApp.getProductTest_Codehaus();
		

	}// End of main
	
	
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void listProductGet_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/listProduct";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		//System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object > map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n"+ map);
		//Search search = (Search)map.get("search");
		//List<Product> listProduct =  (List<Product>) map.get("list");
		//System.out.println("search :: "+search);
		//System.out.println("List :: "+listProduct);
		
	}
	
	public static void listProductPost_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:8080/product/json/listProduct";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		// �ٵ� ����
		Search search = new Search();
	 	search.setCurrentPage(2);
	 	
	 	ObjectMapper objectMapper = new ObjectMapper();
	 	
	 	String jsonValue = objectMapper.writeValueAsString(search);
		
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		//System.out.println(jsonobj);
	
		Map<String, Object > map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n"+ map);
		//Search search = (Search)map.get("search");
		//List<Product> listProduct =  (List<Product>) map.get("list");
		//System.out.println("search :: "+search);
		//System.out.println("List :: "+listProduct);
		
	}

	public static void getProductTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:8080/product/json/getProduct/10004";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 Product product = objectMapper.readValue(jsonobj.toString(), Product.class);
		 System.out.println(product);
	}
}// End of class