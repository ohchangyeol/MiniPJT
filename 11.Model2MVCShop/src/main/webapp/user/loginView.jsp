<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


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
	
	<link rel="stylesheet" href="../css/signin.css" >
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		.container{text-align: center;}
	</style>
    
    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">

		//============= "�α���"  Event ���� =============
		$( function() {
			
			$("#userId").focus();
			
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("button").on("click" , function() {
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {
					alert('ID �� �Է����� �����̽��ϴ�.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('�н����带 �Է����� �����̽��ϴ�.');
					$("#password").focus();
					return;
				}
				//console.log(typeof id, typeof pw);
				
				//$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
				$.ajax({
				    url:'/user/json/login', // ��û �� �ּ�
				    async:true,// false �� ��� ���� ��û���� ����
				    type:'POST', // GET, PUT
				    headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
				    data: JSON.stringify({
				        "userId":id,
				        "password":pw
				    }),// ������ ������
				    dataType:'json',// xml, json, script, html
				    //beforeSend:function(JSONData) {},// ���� ��û �� ȣ�� �Ǵ� �Լ� return false; �� ��� ��û �ߴ�
				    success:function(JSONData) {
				    	console.log(JSONData);
				    	if(JSONData.password == pw){
				    		$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
				    	}else{
					    	$(".append-box").append("<div class='flash'>�߸��� ���̵�� �н����� �Դϴ�.</div>");
				    	}
				    	//
				    },// ��û �Ϸ� ��
				    error:function(JSONData) {
				    	$(".append-box").append("<div class='flash'>�߸��� ���̵�� �н����� �Դϴ�.</div>");
				    }// ��û ����.
				    //complete:function(JSONData) {}// ��û�� ����, ������ ��� ���� �Ϸ� �� ��� ȣ��
				});
				
			});
		});	
		
		
		//============= ȸ��������ȭ���̵� =============
		$( function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
	</script>		
	
</head>

<body>

	
	<!--  ȭ�鱸�� div Start /////////////////////////////////////-->
	
    <div class="container">

      <h2 class="form-signin-heading">Model2 MVC Shop</h2>
      <div class="append-box">
      </div>
      <form class="form-signin">
        <h3 class="form-signin-heading">User Id</h3>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="userId" class="form-control" placeholder="User Id" name="userId">
        <h3 class="form-signin-heading">Password</h3>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" placeholder="Password" name="password">
        
        <button class="btn btn-lg btn-success btn-block" type="button">Sign in</button>
      </form>
	  <div class="new-mvc">
	  	New to MVC Shop? 
	  	<a href="/user/addUser">Create an account</a>.
	  </div>
    </div> <!-- /container -->
 	<!--  ȭ�鱸�� div end /////////////////////////////////////-->

</body>

</html>