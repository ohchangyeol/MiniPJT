<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function history(){
	popWin = window.open("/history.jsp","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
</script>
<!-- ToolBar Start /////////////////////////////////////-->
<div class="navbar  navbar-inverse navbar-fixed-top">
	
	<div class="container">
	       
		<a class="navbar-brand" href="/index.jsp">Model2 MVC Shop</a>
		
		<!-- toolBar Button Start //////////////////////// -->
		<div class="navbar-header">
		    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		</div>
		<!-- toolBar Button End //////////////////////// -->
		
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="target" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar �� �پ��ϰ� ����ϸ�.... -->
	             <ul class="nav navbar-nav">
	             <c:if test="${ !empty user }">
		              <!--  ȸ������ DrowDown -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >ȸ������</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">����������ȸ</a></li>
		                         
		                         <c:if test="${sessionScope.user.role == 'admin'}">
		                         	<li><a href="#">ȸ��������ȸ</a></li>
		                         </c:if>
		                         
		                         <li class="divider"></li>
		                         <li><a href="#">etc...</a></li>
		                     </ul>
		                 </li>
		                 
		              <!-- �ǸŻ�ǰ���� DrowDown  -->
		               <c:if test="${sessionScope.user.role == 'admin'}">
			              <li class="dropdown">
			                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
			                         <span >�ǸŻ�ǰ����</span>
			                         <span class="caret"></span>
			                     </a>
			                     <ul class="dropdown-menu">
			                         <li><a href="#">�ǸŻ�ǰ���</a></li>
			                         <li><a href="#">�ǸŻ�ǰ����</a></li>
			                         <li class="divider"></li>
			                         <li><a href="#">etc..</a></li>
			                     </ul>
			                </li>
		                 </c:if>
		                 
		              <!-- ���Ű��� DrowDown -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >��ǰ����</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">�� ǰ �� ��</a></li>
		                         
		                         <c:if test="${sessionScope.user.role == 'user'}">
		                           <li><a href="#">�����̷���ȸ</a></li>
		                         </c:if>
		                         
		                         <li><a href="javascript:history()">�ֱٺ���ǰ</a></li>
		                         <li class="divider"></li>
		                         <li><a href="#">etc..</a></li>
		                     </ul>
		                 </li>
	                 </c:if>
	                 
	             </ul>
	             
	             <ul class="nav navbar-nav navbar-right">
	             	<c:if test="${ !empty user }">
	             		<li><a>${user.userName}�� ȯ���մϴ�.</a></li>
	                	<li><a href="#">Sign out</a></li>
	             	</c:if>
	             	<c:if test="${ empty user }">
	             		<li><a href="#">Sign in</a></li>
	             		<li><a href="#">Sign up</a></li>
	             	</c:if>
	            </ul>
		</div>
		<!-- dropdown hover END -->	       
	    
	</div>
</div>
		<!-- ToolBar End /////////////////////////////////////-->
 	
   	
   	
   	<script type="text/javascript">
	
		//============= logout Event  ó�� =============	
		 $(function() {
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('Sign out')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
				//self.location = "/user/logout"
			}); 
		 
		
		//============= ȸ��������ȸ Event  ó�� =============	
			//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('ȸ��������ȸ')").on("click" , function() {
				//$(self.location).attr("href","/user/logout");
				self.location = "/user/listUser"
			}); 
		
		//=============  ����������ȸȸ Event  ó�� =============	
		 	$( "a:contains('����������ȸ')" ).on("click" , function() {
		 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$(self.location).attr("href","/user/getUser?userId=${sessionScope.user.userId}");
			});
		
		 	$( "a:contains('�����̷���ȸ')" ).on("click" , function() {
		 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$(self.location).attr("href","/purchase/listPurchase");
			});
		 	
		 	$( "a:contains('�� ǰ �� ��')" ).on("click" , function() {
		 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$(self.location).attr("href","/product/listProduct?menu=search");
			});
		 	
		 	$( "a:contains('�ǸŻ�ǰ����')" ).on("click" , function() {
		 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$(self.location).attr("href","/product/listProduct?menu=manage");
			});
		 	
		 	$( "a:contains('�ǸŻ�ǰ���')" ).on("click" , function() {
		 		//==> DOM Object GET 3���� ��� ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
				$(self.location).attr("href","/product/addProduct");
			});
		 	$("a[href='#' ]:contains('Sign up')").on("click" , function() {
				self.location = "/user/addUser"
			});
		 	$("a[href='#' ]:contains('Sign in')").on("click" , function() {
				self.location = "/user/login"
			});
		 });
		
	</script>  