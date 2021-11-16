
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- ���� : http://getbootstrap.com/css/   ���� -->
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
   <!-- �޷� -->
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
		
	
		//============= "����"  Event ���� =============
		 $(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-primary" ).on("click" , fncAddPurchase);
		
		//============= "���"  Event ó�� ��  ���� =============
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		
		
		///////////////////////////////////////////////////////////////////////
		function fncAddPurchase() {
			
			$("form[name=detailForm]").attr("method" , "POST").attr("action" , "/purchase/addPurchase").submit();
		};
		
		var datep = {

			    format: "yyyy-mm-dd", //������ ���� ����(yyyy : �� mm : �� dd : �� )
		        startDate: '-10d', //�޷¿��� ���� �� �� �ִ� ���� ���� ��¥. �������δ� ���� �Ұ��� ( d : �� m : �� y : �� w : ��)
		        endDate: '+10d', //�޷¿��� ���� �� �� �ִ� ���� ���� ��¥. ���ķ� ���� �Ұ� ( d : �� m : �� y : �� w : ��)
		        autoclose : true, //����ڰ� ��¥�� Ŭ���ϸ� �ڵ� Ķ������ ������ �ɼ�
		        calendarWeeks : false, //Ķ���� ���� �� �������� �����ִ� �ɼ� �⺻�� false �����ַ��� true
		        clearBtn : false, //��¥ ������ �� �ʱ�ȭ ���ִ� ��ư �����ִ� �ɼ� �⺻�� false �����ַ��� true
		        datesDisabled : [''],//���� �Ұ����� �� ���� �ϴ� �迭 ���� �ִ� format �� ������ ���ƾ���.
		        daysOfWeekDisabled : [0,6], //���� �Ұ����� ���� ���� 0 : �Ͽ��� ~ 6 : �����
		        daysOfWeekHighlighted : [3], //���� �Ǿ�� �ϴ� ���� ����
		        disableTouchKeyboard : false, //����Ͽ��� �÷����� �۵� ���� �⺻�� false �� �۵� true�� �۵� ����.
		        immediateUpdates: false, //����ڰ� ���� ȭ������ �ٷιٷ� ��¥�� �������� ���� �⺻�� :false 
		        multidate : false, //���� ��¥ ������ �� �ְ� �ϴ� �ɼ� �⺻�� :false 
		        multidateSeparator :",", //���� ��¥�� �������� �� ���̿� ��Ÿ���� ��¥ 2019-05-01,2019-06-01
		        templates : {
		            leftArrow: ' < ',
		            rightArrow: ' > '
		        }, //������ �����޷� �Ѿ�� ȭ��ǥ ��� Ŀ���� ����¡ 
		        showWeekDays : true ,// ���� ���� �����ִ� �ɼ� �⺻�� : true
		        todayHighlight : true , //���� ��¥�� ���̶����� ��� �⺻�� :false 
		        toggleActive : true, //�̹� ���õ� ��¥ �����ϸ� �⺻�� : false�ΰ�� �״�� ���� true�� ��� ��¥ ����
		        weekStart : 0 ,//�޷� ���� ���� �����ϴ� �� �⺻���� 0�� �Ͽ��� 
		        language : "ko" //�޷��� ��� ����, �׿� �´� js�� ��ü������Ѵ�.
		  }

		  $('#divyDate').datepicker(datep);
			
		
	 });	
	
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">��ǰ ����</h3>
	       
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form name="detailForm" class="form-horizontal">
            <div class="img-box">
                <img src="/images/uploadFiles/${product.fileName}">
            </div>
		  <input type="hidden" name="prodNo" value="${product.prodNo}" />
		  <div class="form-group">
		    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">�� ǰ ��</label>
		    <div class="col-sm-4 prod-text">
		      <input type="hidden" class="form-control" id="prodName" name="prodName" value="${product.prodName}">
              ${product.prodName}
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">���� ����</label>
		    <div class="col-sm-4 prod-text">
		      <input type="hidden" class="form-control" id="date_" name="manuDate"  value="${product.manuDate}">
              ${product.manuDate}
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">�� ��</label>
		    <div class="col-sm-4 prod-text">
		    <div class="input-group">
		      <input type="hidden" class="form-control" id="price" name="price"  placeholder="��ǰ ����" value="${product.price}">
              ${product.price}��
		     
		    </div> 
		    </div> 
		  </div>
		  <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">��ǰ �� ����</label>
		    <div class="col-sm-4 prod-text">
              <input type="hidden" class="form-control" id="prodDetail" name="prodDetail"value="${product.prodDetail}">
              ${product.prodDetail}
		    </div>
		  </div>
          <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">������ ���̵�</label>
		    <div class="col-sm-4 prod-text">
              <input type="hidden" class="form-control" id="userId" name="userId"value="${user.userId}">
              ${user.userId}
		    </div>
		  </div>
          <div class="form-group">
		    <label for="paymentOption" class="col-sm-offset-1 col-sm-3 control-label">���Ź��</label>
		    <div class="col-sm-4">
		      
              <select class="form-control" name="paymentOption">
                <option value="1"selected="selected">���� ����</option>
                <option value="2">�ſ� ����</option>
                
              </select>
		    </div>
		  </div>
          <div class="form-group">
		    <label for="receiverName" class="col-sm-offset-1 col-sm-3 control-label">������ �̸�</label>
		    <div class="col-sm-4">
              <input type="text" class="form-control" id="receiverName" name="receiverName"value="${user.userName}">
              
		    </div>
		  </div>
          <div class="form-group">
		    <label for="receiverPhone" class="col-sm-offset-1 col-sm-3 control-label">������ ����ó</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="������"> -->
              <input type="text" class="form-control" id="receiverPhone" name="receiverPhone"value="${user.phone}">
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyAddr" class="col-sm-offset-1 col-sm-3 control-label">������ �ּ�</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="������"> -->
              <input type="text" class="form-control" id="divyAddr" name="divyAddr"value="${user.addr}">
             
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyDate" class="col-sm-offset-1 col-sm-3 control-label">��� ��� ��¥</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="divyDate" name="divyDate" placeholder="���� ���ڸ� �������ּ���.">
		    </div>
		  </div>
          <div class="form-group">
		    <label for="divyRequest" class="col-sm-offset-1 col-sm-3 control-label">��û ����</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="������"> -->
              <textarea name="divyRequest" class="form-control" rows="3" placeholder="��۽� ��û ���� �Է����ּ���."></textarea>
		    </div>
		  </div>

          <!-- ��ư -->
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >�� &nbsp;��</button>
			  <a class="btn btn-primary btn" href="javascript:history.go(-1)" role="button">�� &nbsp;��</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
 	
</body>

</html>