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
	
	<!--  CSS �߰� : ���ٿ� ȭ�� ������ ���� �ذ� :  �ּ�ó�� ��, �� Ȯ��-->
	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	 	
    <title>���� �����ȸ</title>
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
        			alert("��ǰ ������ �Ұ��� �մϴ�.");
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
    														+"��ǰ �� : "+JSONData.purchaseProd.prodName+"<br/>"
    														+"��ǰ�̹��� : <img width='150' src='/images/uploadFiles/"+JSONData.purchaseProd.fileName+"'><br/>"
    														+"��ǰ������ : "+JSONData.purchaseProd.prodDetail+"<br/>"
    														+"�������� : "+JSONData.purchaseProd.manuDate+"<br/>"
    														+"�� �� : "+JSONData.purchaseProd.price+"<br/>"
    														+"��� ���� : "+JSONData.purchaseProd.regDate+"<br/><br/>";
    														
    							// if(JSONData.tranCode == '1  '){
    							// 	//displayValue += "<button onclick='location.href=/purchase/addPurchase?prod_no="+JSONData.prodNo+"'> ���� </button>";
    							// 	//displayValue += "<a href=/purchase/addPurchase?prod_no="+JSONData.prodNo+"> ���� </a>";
    							// 	displayValue += "<button type='button' onclick = 'location.href=\"/purchase/addPurchase?prod_no="+JSONData.prodNo+"\"'> ���� </button>";
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
        		console.log("�ù߿־ȵ�");
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
															+"���̵� : "+JSONData.userId+"<br/>"
															+"��  �� : "+JSONData.userName+"<br/>"
															+"�̸��� : "+JSONData.email+"<br/>"
															+"ROLE : "+JSONData.role+"<br/>"
															+"����� : "+JSONData.regDateString+"<br/>"
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
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-info">
	       <h3>�����̷���ȸ</h3>
	    </div>
	    
	    <!-- table ���� �˻� Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		��ü  ${resultPage.totalCount } �Ǽ�, ���� ${resultPage.currentPage}  ������
		    	</p>
		    </div>
		    
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >ȸ�� ID</th>
            <th align="left">ȸ����</th>
            <th align="left">��ȭ��ȣ</th>
            <th align="left">�����Ȳ</th>
            <th align="left">��������</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="purchase" items="${list}">
		  	
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center"class="tran-btn" data-tranno="${purchase.tranNo}" >${ i }</td>
			  <td align="left"  title="Click : ȸ������ Ȯ��" class="getuser-btn">${purchase.buyer.userId}</td>
			  <td align="left">${purchase.receiverName}</td>
			  <td align="left">${purchase.receiverPhone}</td>
			  <td align="left" data-trancode="${purchase.tranCode}">
			  	<c:choose>
				    <c:when test="${purchase.tranCode== '1  '}">
				    	���� �Ϸ�
				    </c:when>
				    <c:when test="${purchase.tranCode== '2  '}">
				    	�����
				    </c:when>
				    <c:otherwise>
						����Ȯ��
					</c:otherwise>
				</c:choose>
			  </td>
			  <td align="left">
			  <c:if test="${purchase.tranCode == '2  '}">
	            <a href ="/purchase/updateTranCode?prodNo=${purchase.purchaseProd.prodNo}" onclick='return confirm("������ ���� �ϼ̽��ϱ� ?");'>���ǵ���</a>
            </c:if></td>
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	<!--  ȭ�鱸�� div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
    
    </body>
</html>