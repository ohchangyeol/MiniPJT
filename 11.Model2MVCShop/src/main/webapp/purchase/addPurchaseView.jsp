
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
   <!-- 달력 -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<!-- <script src="/javascript/datePicker.js"></script> -->
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		body {
            padding-top : 50px;
        }
        .img-box{text-align: center;margin-bottom: 50px;}
        .img-box img{width: 300px;}
        .prod-text{padding-top:7px}
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		
	
		//============= "수정"  Event 연결 =============
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-primary" ).on("click" , fncAddPurchase);
		
		//============= "취소"  Event 처리 및  연결 =============
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		
		
		///////////////////////////////////////////////////////////////////////
		function fncAddPurchase() {
			
			$("form[name=detailForm]").attr("method" , "POST").attr("action" , "/purchase/addPurchase").submit();
		};
		
		var datep = {

			    format: "yyyy-mm-dd", //데이터 포맷 형식(yyyy : 년 mm : 월 dd : 일 )
		        startDate: '-10d', //달력에서 선택 할 수 있는 가장 빠른 날짜. 이전으로는 선택 불가능 ( d : 일 m : 달 y : 년 w : 주)
		        endDate: '+10d', //달력에서 선택 할 수 있는 가장 느린 날짜. 이후로 선택 불가 ( d : 일 m : 달 y : 년 w : 주)
		        autoclose : true, //사용자가 날짜를 클릭하면 자동 캘린더가 닫히는 옵션
		        calendarWeeks : false, //캘린더 옆에 몇 주차인지 보여주는 옵션 기본값 false 보여주려면 true
		        clearBtn : false, //날짜 선택한 값 초기화 해주는 버튼 보여주는 옵션 기본값 false 보여주려면 true
		        datesDisabled : [''],//선택 불가능한 일 설정 하는 배열 위에 있는 format 과 형식이 같아야함.
		        daysOfWeekDisabled : [0,6], //선택 불가능한 요일 설정 0 : 일요일 ~ 6 : 토요일
		        daysOfWeekHighlighted : [3], //강조 되어야 하는 요일 설정
		        disableTouchKeyboard : false, //모바일에서 플러그인 작동 여부 기본값 false 가 작동 true가 작동 안함.
		        immediateUpdates: false, //사용자가 보는 화면으로 바로바로 날짜를 변경할지 여부 기본값 :false 
		        multidate : false, //여러 날짜 선택할 수 있게 하는 옵션 기본값 :false 
		        multidateSeparator :",", //여러 날짜를 선택했을 때 사이에 나타나는 글짜 2019-05-01,2019-06-01
		        templates : {
		            leftArrow: ' < ',
		            rightArrow: ' > '
		        }, //다음달 이전달로 넘어가는 화살표 모양 커스텀 마이징 
		        showWeekDays : true ,// 위에 요일 보여주는 옵션 기본값 : true
		        todayHighlight : true , //오늘 날짜에 하이라이팅 기능 기본값 :false 
		        toggleActive : true, //이미 선택된 날짜 선택하면 기본값 : false인경우 그대로 유지 true인 경우 날짜 삭제
		        weekStart : 0 ,//달력 시작 요일 선택하는 것 기본값은 0인 일요일 
		        language : "ko" //달력의 언어 선택, 그에 맞는 js로 교체해줘야한다.
		  }

		  $('#divyDate').datepicker(datep);
			
		
	 });	
	
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">상품 구매</h3>
	       
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form name="detailForm" class="form-horizontal">
            <div class="img-box">
                <img src="/images/uploadFiles/${product.fileName}">
            </div>
		  <input type="hidden" name="prodNo" value="${product.prodNo}" />
		  <div class="form-group">
		    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
		    <div class="col-sm-4 prod-text">
		      <input type="hidden" class="form-control" id="prodName" name="prodName" value="${product.prodName}">
              ${product.prodName}
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">제조 일자</label>
		    <div class="col-sm-4 prod-text">
		      <input type="hidden" class="form-control" id="date_" name="manuDate"  value="${product.manuDate}">
              ${product.manuDate}
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">가 격</label>
		    <div class="col-sm-4 prod-text">
		    <div class="input-group">
		      <input type="hidden" class="form-control" id="price" name="price"  placeholder="상품 가격" value="${product.price}">
              ${product.price}원
		     
		    </div> 
		    </div> 
		  </div>
		  <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">상품 상세 정보</label>
		    <div class="col-sm-4 prod-text">
              <input type="hidden" class="form-control" id="prodDetail" name="prodDetail"value="${product.prodDetail}">
              ${product.prodDetail}
		    </div>
		  </div>
          <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">구매자 아이디</label>
		    <div class="col-sm-4 prod-text">
              <input type="hidden" class="form-control" id="userId" name="userId"value="${user.userId}">
              ${user.userId}
		    </div>
		  </div>
          <div class="form-group">
		    <label for="paymentOption" class="col-sm-offset-1 col-sm-3 control-label">구매방법</label>
		    <div class="col-sm-4">
		      
              <select class="form-control" name="paymentOption">
                <option value="1"selected="selected">현금 구매</option>
                <option value="2">신용 구매</option>
                
              </select>
		    </div>
		  </div>
          <div class="form-group">
		    <label for="receiverName" class="col-sm-offset-1 col-sm-3 control-label">구매자 이름</label>
		    <div class="col-sm-4">
              <input type="text" class="form-control" id="receiverName" name="receiverName"value="${user.userName}">
              
		    </div>
		  </div>
          <div class="form-group">
		    <label for="receiverPhone" class="col-sm-offset-1 col-sm-3 control-label">구매자 연락처</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="상세정보"> -->
              <input type="text" class="form-control" id="receiverPhone" name="receiverPhone"value="${user.phone}">
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyAddr" class="col-sm-offset-1 col-sm-3 control-label">구매자 주소</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="상세정보"> -->
              <input type="text" class="form-control" id="divyAddr" name="divyAddr"value="${user.addr}">
             
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyDate" class="col-sm-offset-1 col-sm-3 control-label">배송 희망 날짜</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="divyDate" name="divyDate" placeholder="제조 일자를 선택해주세요.">
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyRequest" class="col-sm-offset-1 col-sm-3 control-label">요청 사항</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="상세정보"> -->
              <textarea name="divyRequest" class="form-control" rows="3" placeholder="배송시 요청 사항 입력해주세요."></textarea>
		    </div>
		  </div>

          <!-- 버튼 -->
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >구 &nbsp;매</button>
			  <a class="btn btn-primary btn" href="javascript:history.go(-1)" role="button">취 &nbsp;소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  화면구성 div Start /////////////////////////////////////-->
 	
</body>

</html>