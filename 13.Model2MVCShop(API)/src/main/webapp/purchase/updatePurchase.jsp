<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
 		body {
            padding-top : 50px;
        }
     </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//============= 회원정보수정 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( ".close-btn" ).on("click" , function() {
                self.location = "/purchase/listPurchase"
            });
			$( ".update-btn" ).on("click" , function(e) {
                self.location = "/purchase/updatePurchase?tranNo="+$(e.target).data("tranno");
            });
           
		});
		
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header">
	       <h3 class=" text-info">구매 내역 보기</h3>
           <h5 class="text-muted">${purchase.receiverName}님이 <strong class="text-danger">구매</strong>하신 상품입니다.</h5>
	    </div>
	
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>상 품 명</strong></div>
			<div class="col-xs-8 col-md-4">${purchase.purchaseProd.prodName}</div>
		</div>
		
		<hr/>
		
        <div class="row">
            <div class="col-xs-4 col-md-2 "><strong>구매자 아이디</strong></div>
          <div class="col-xs-8 col-md-4">${purchase.buyer.userId}</div>
      </div>
      
      <hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>구매 방법</strong></div>
			<div class="col-xs-8 col-md-4">
                <c:choose>
                    <c:when test="${purchase.paymentOption == 1}">현금구매</c:when>
                    <c:otherwise>신용구매</c:otherwise>
                </c:choose>

            </div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>구매자 이름</strong></div>
			<div class="col-xs-8 col-md-4">${purchase.receiverName}	</div>
		</div>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>구매자 연락처</strong></div>
			<div class="col-xs-8 col-md-4">${purchase.receiverPhone}</div>
		</div>
		<hr/>
		
        
        <div class="row">
            <div class="col-xs-4 col-md-2"><strong>구매자 주소</strong></div>
            <div class="col-xs-8 col-md-4">${purchase.divyAddr}</div>
        </div>
		<hr/>

		<div class="row">
            <div class="col-xs-4 col-md-2"><strong>배송 희망 날짜</strong></div>
            <div class="col-xs-8 col-md-4">${purchase.divyDate}</div>
        </div>
		<hr/>

        <div class="row">
            <div class="col-xs-4 col-md-2"><strong>요청 사항</strong></div>
            <div class="col-xs-8 col-md-4">${purchase.divyRequest}</div>
        </div>
		<hr/>
		<div class="row">
            <div class="col-xs-4 col-md-2"><strong>주문일</strong></div>
            <div class="col-xs-8 col-md-4">${purchase.orderDate}</div>
        </div>
		<hr/>
		
		<div class="row">
	  		<div class="col-md-12 text-center ">
	  			
	  			<button type="button" class="btn btn-primary update-btn" data-tranno="${purchase.tranNo}">재 수 정</button>
	  			<button type="button" class="btn btn-primary close-btn">확 인</button>
                
	  		</div>
		</div>
		
		<br/>
		
 	</div>
 	<!--  화면구성 div Start /////////////////////////////////////-->

</body>

</html>