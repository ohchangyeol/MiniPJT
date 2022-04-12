<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
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
	<script src="/javascript/datePicker.js"></script>
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
		
	
		//============= "����"  Event ���� =============
		 $(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-primary" ).on("click" , fncAddProduct);
		
		//============= "���"  Event ó�� ��  ���� =============
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				$("form")[0].reset();
			});
		
		///////////////////////////////////////////////////////////////////////
		function fncAddProduct() {
			var prodName=$("input[name='prodName']").val();
			var proPrice = $("#price").val();
			if(prodName == null || prodName.length <1){
				alert("��ǰ����  �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
			if(proPrice == null || prodName.length <1){
				alert("��������  �ݵ�� �Է��ϼž� �մϴ�.");
				return;
			}
				
			$("form[name=detailForm]").attr("method" , "POST").attr("action" , "/product/addProduct").submit();
		}
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
	       <h3 class=" text-info">��ǰ ���</h3>
	       
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form name="detailForm" class="form-horizontal" enctype="multipart/form-data">
		
		  <div class="form-group">
		    <label for="prodName" class="col-sm-offset-1 col-sm-3 control-label">�� ǰ ��</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="prodName" name="prodName"placeholder="��ǰ ���� �Է� ���ּ���.">
		       
		    </div>
		  </div>
			<div class="form-group">
			  <label for="file" class="col-sm-offset-1 col-sm-3 control-label">��ǰ�̹���</label>
			  <div class="col-sm-4">
			    <input type="file" id="file" name="file">
			  </div>
			</div>
		  
		  
		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">���� ����</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="date_" name="manuDate" placeholder="���� ���ڸ� �������ּ���.">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="price" class="col-sm-offset-1 col-sm-3 control-label">�� ��</label>
		    <div class="col-sm-4">
		    <div class="input-group">
		      <input type="text" class="form-control" id="price" name="price"  placeholder="��ǰ ����">
		      <div class="input-group-addon">��</div>
		    </div> 
		    </div> 
		  </div>

		  <div class="form-group">
		    <label for="manuDate" class="col-sm-offset-1 col-sm-3 control-label">��ǰ ����</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="prodCount" name="prodCount" placeholder="��ǰ ������ �Է��ϼ���." value="1">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="prodDetail" class="col-sm-offset-1 col-sm-3 control-label">��ǰ �� ����</label>
		    <div class="col-sm-4">
		      <!-- <input type="text" class="form-control" id="prodDetail" placeholder="������"> -->
		      <textarea name="prodDetail" class="form-control" rows="3" placeholder="��ǰ �� ������ �Է����ּ���"></textarea>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >�� &nbsp;��</button>
			  <a class="btn btn-primary btn" href="#" role="button">�� &nbsp;��</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
 	
</body>

</html>