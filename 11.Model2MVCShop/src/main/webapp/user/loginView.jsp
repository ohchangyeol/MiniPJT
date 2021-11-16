<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


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
	
	<link rel="stylesheet" href="../css/signin.css" >
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		.container{text-align: center;}
	</style>
    
    <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">

		//============= "로그인"  Event 연결 =============
		$( function() {
			
			$("#userId").focus();
			
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("button").on("click" , function() {
				var id=$("input:text").val();
				var pw=$("input:password").val();
				
				if(id == null || id.length <1) {
					alert('ID 를 입력하지 않으셨습니다.');
					$("#userId").focus();
					return;
				}
				
				if(pw == null || pw.length <1) {
					alert('패스워드를 입력하지 않으셨습니다.');
					$("#password").focus();
					return;
				}
				
				$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
			});
		});	
		
		
		//============= 회원원가입화면이동 =============
		$( function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				self.location = "/user/addUser"
			});
		});
		
	</script>		
	
</head>

<body>

	
	<!--  화면구성 div Start /////////////////////////////////////-->
	
    <div class="container">

      <h2 class="form-signin-heading">Model2 MVC Shop</h2>
      <form class="form-signin">
        <h3 class="form-signin-heading">User Id</h3>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input type="text" id="userId" class="form-control" placeholder="User Id" name="userId">
        <h3 class="form-signin-heading">Password</h3>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" placeholder="Password" name="password">
        
        <button class="btn btn-lg btn-success btn-block" type="submit">Sign in</button>
      </form>
	  <div class="new-mvc">
	  	New to MVC Shop? 
	  	<a href="/user/addUser">Create an account</a>.
	  </div>
    </div> <!-- /container -->
 	<!--  화면구성 div end /////////////////////////////////////-->

</body>

</html>