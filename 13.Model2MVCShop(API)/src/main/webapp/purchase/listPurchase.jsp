<%-- <%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.common.*" %> --%>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
List<Purchase> list = (List<Purchase>)request.getAttribute("list");

Search search=(Search)request.getAttribute("search");

String urlMenu = request.getParameter("menu");

Page resultPage=(Page)request.getAttribute("resultPage");

--%>



<html>
    <head>
    
    
    <meta charset="EUC-KR">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 	
    <title>구매 목록조회</title>
    <style>
	  body {
            padding-top : 50px;
        }
    </style>
    <script type="text/javascript">
        function fncGetUserList(currentPage) {
        	document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
        
        $(document).ready(function(){
        	//console.log("hello world")
        	$(".tran-btn").on("click", function(e){
        		//console.log($(e.currentTarget).nextAll("td[data-trancode]"));
        		//console.log($(e.currentTarget).data("value"));
        		
        		var tranCode = $(e.currentTarget).nextAll("td[data-trancode]").data('trancode').trim();
        		console.log(tranCode);
        		var tranNo = $(e.target).data("tranno");
        		console.log(tranNo);
        		//alert("tranNo" + tranNo);
        		if(tranCode == '1'){
        			self.location ="/purchase/getPurchase?tranNo="+tranNo;	
        		}else{
        			alert("상품 수정이 불가능 합니다.");
        		}
        		
        		 
        		
        		  /* $.ajax( 
    					{
    						url : "/purchase/getPurchase?tranNo="+tranNo,
    						method : "GET" ,
    						dataType : "json" ,
    						headers : {
    							"Accept" : "application/json",
    							"Content-Type" : "application/json"
    						},
    						success : function(JSONData , status) {
    							//Debug...
    							alert("status");
    							//Debug...
    							//alert("JSONData : \n"+JSONData);
    							//console.log(status);
    							var displayValue = "<h3>"
    														+"상품 명 : "+JSONData.purchaseProd.prodName+"<br/>"
    														+"상품이미지 : <img width='150' src='/images/uploadFiles/"+JSONData.purchaseProd.fileName+"'><br/>"
    														+"상품상세정보 : "+JSONData.purchaseProd.prodDetail+"<br/>"
    														+"제조일자 : "+JSONData.purchaseProd.manuDate+"<br/>"
    														+"가 격 : "+JSONData.purchaseProd.price+"<br/>"
    														+"등록 일자 : "+JSONData.purchaseProd.regDate+"<br/><br/>";
    														
    							// if(JSONData.tranCode == '1  '){
    							// 	//displayValue += "<button onclick='location.href=/purchase/addPurchase?prod_no="+JSONData.prodNo+"'> 구매 </button>";
    							// 	//displayValue += "<a href=/purchase/addPurchase?prod_no="+JSONData.prodNo+"> 구매 </a>";
    							// 	displayValue += "<button type='button' onclick = 'location.href=\"/purchase/addPurchase?prod_no="+JSONData.prodNo+"\"'> 구매 </button>";
    							// }
    							displayValue += '</h3>';
    														
    							//Debug...									
    							//alert(displayValue);
    							$("h3").remove();
    							
    							$( "#"+tranNo+"" ).html(displayValue);
    						}
    				});  */
        	})
        	
        	$(".getuser-btn").on("click", function(e){
        		self.location ="/user/getUser?userId="+$(this).text().trim();
        		/* var userId = $(this).text().trim();
        		console.log("시발왜안돼");
        		console.log(userId);
				$.ajax( 
						{
							url : "/user/json/getUser/"+userId ,
							method : "GET" ,
							dataType : "json" ,
							headers : {
								"Accept" : "application/json",
								"Content-Type" : "application/json"
							},
							success : function(JSONData , status) {

								//Debug...
								//alert(status);
								//Debug...
								//alert("JSONData : \n"+JSONData);
								
								var displayValue = "<h3>"
															+"아이디 : "+JSONData.userId+"<br/>"
															+"이  름 : "+JSONData.userName+"<br/>"
															+"이메일 : "+JSONData.email+"<br/>"
															+"ROLE : "+JSONData.role+"<br/>"
															+"등록일 : "+JSONData.regDateString+"<br/>"
															+"</h3>";
								//Debug...									
								//alert(displayValue);
								$("h3").remove();
								$( "."+userId+"" ).html(displayValue);
							}
					}); */
        	})
        })
    </script>
    </head>
    
    <body bgcolor="#ffffff" text="#000000">
    
    <!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-info">
	       <h3>구매이력조회</h3>
	    </div>
	    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >회원 ID</th>
            <th align="left">회원명</th>
            <th align="left">전화번호</th>
            <th align="left">배송현황</th>
            <th align="left">정보수정</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
		  	
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center"class="tran-btn" data-tranno="${purchase.tranNo}" >${ i }</td>
			  <td align="left"  title="Click : 회원정보 확인" class="getuser-btn">${purchase.buyer.userId}</td>
			  <td align="left">${purchase.receiverName}</td>
			  <td align="left">${purchase.receiverPhone}</td>
			  <td align="left" data-trancode="${purchase.tranCode}">
			  	<c:choose>
				    <c:when test="${purchase.tranCode== '1  '}">
				    	구매 완료
				    </c:when>
				    <c:when test="${purchase.tranCode== '2  '}">
				    	배송중
				    </c:when>
				    <c:otherwise>
						구매확정
					</c:otherwise>
				</c:choose>
			  </td>
			  <td align="left">
			  <c:if test="${purchase.tranCode == '2  '}">
	            <a href ="/purchase/updateTranCode?prodNo=${purchase.purchaseProd.prodNo}" onclick='return confirm("물픔을 수령 하셨습니까 ?");'>물건도착</a>
            </c:if></td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
    
    </body>
</html>