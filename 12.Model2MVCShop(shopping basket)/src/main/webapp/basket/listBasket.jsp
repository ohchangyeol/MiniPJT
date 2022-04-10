<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	<title>��ٱ���</title>
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
	
	<style>
		table td{vertical-align: middle !important;}
		.table-flex{display: flex; align-items: center;}
		.price-box{text-align: center;}
		.bottom-box{justify-content: space-between;}
		.price-box > div{padding-right: 10px;}
		.table-bordered tbody tr td:first-child{background-color: #f8f8f8; text-align: right; vertical-align: top;}
		.table-bordered input {border: none; border-bottom: 1px solid #9b9b9b;}
		.table-title{background-color: #fff !important; text-align: initial !important;}
		table textarea{resize: none; border: 1px solid #9b9b9b;}
		.buy{width: 50%; font-size: 2rem; padding: 7px 0 7px 0; margin: 0 auto 20px auto; display: block;}
		
	</style>

	<script type="text/javascript">
		$(document).ready(function() {

			$("#cbx_chkAll").click(function() {
				if($("#cbx_chkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
				else $("input[name=chk]").prop("checked", false);
			});
			
			$("input[name=chk]").click(function() {
				const total = $("input[name=chk]").length;
				const checked = $("input[name=chk]:checked").length;
				
				if(total != checked) $("#cbx_chkAll").prop("checked", false);
				else $("#cbx_chkAll").prop("checked", true); 
			});

			$("#select-delete").on('click', ()=>{
				let checkBoxArr = []; 
				$("input:checkbox[name='chk']:checked").each(function() {
					checkBoxArr.push($(this).val());
					$(this).closest('tr').remove();
				});
				console.log(checkBoxArr);
				$.ajax({
					type  : "POST",
					url    : "/basket/json/deleteBasket",
					data: {
					"prodList" : checkBoxArr,
					"user" : $("#user-id").data("user")
					},
					dataType : "json" ,
					success: function(result){
						console.log(result);
						alert("�����Ͻ� ��ǰ "+result+"���� �����Ǿ����ϴ�.");
					},
					error: function(xhr, status, error) {
						alert("��ǰ�� ������ �ּ���.");
						
					}  
				});
			})
			$("#all-delete").on('click', ()=>{
				const data = JSON.stringify({
					"buyerId" : $("#user-id").data("user")
				});

				$.ajax({
					type  : "POST",
					url    : "/basket/json/deleteAllBasket",
					
					data : data,
					dataType : "json" ,
					contentType: "application/json; charset=utf-8",
					success: function(result){
						console.log(result);
						alert("�����Ͻ� ��ǰ "+result+"���� �����Ǿ����ϴ�.");
						location.reload();
					},
					error: function(xhr, status, error) {
						alert(error);
						
					}  
				});
			})
			
		});
	</script>
</head>

<body>

	<div id="user-id" class="container" data-user="${user.userId}" >
		<h2>��ٱ���</h2>
		<c:if test="${empty list}">
			��ٱ��� ����� ������ϴ�.
		</c:if>
		<c:if test="${!empty list}">
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<td>
							<input type="checkbox" id="cbx_chkAll">
							��ü����
						</td>
						<td>��ǰ����</td>
						<td>��ǰ�ݾ�</td>
						<td>��ǰ����</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5">�Ǹ��� ��� ��ǰ</td>
					</tr>
					<c:forEach var ="product" items="${list}">
						<tr>
							<td>
								<div class="table-flex">
									<input type="checkbox" name="chk" title="${product.prodName}" value="${product.prodNo}">
								</div>	
							</td>
							<td>
								<div class="table-flex">
									<div>
										<img src="/images/uploadFiles/${product.fileName}" alt="${product.prodName}" width="70">
									</div>
									<div>
										<div>
											${product.prodName}
										</div>
										<div>
											��� : ${product.prodCount}
										</div>
									</div>
								</div>
							</td>
							<td>
									${product.price}��
							</td>
							<td>
								<div>
									<input type="number" style="width: 40px;" value="1">
								</div>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="5">
							<div class="table-flex bottom-box">
								<div>
									<button type="button" id="all-delete">��ü����</button>
									<button type="button" id="select-delete">���û���</button>
								</div>
								<div class="table-flex price-box">
									<div>
										<span>��ǰ����</span>
										<br>
										<span id="total-prod-price">0000</span>
									</div>
									<div> + </div>
									<div>
										<span>��ۺ�</span>
										<br>
										<span id="delivery-price">2500</span>
									</div>
									<div> = </div>
									<div>
										<span>��ǰ ����</span>
										<br>
										<span id="total-price">0000</span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					
				</tbody>
				
			</table>
			<table class="table table-bordered">
				<tbody>
					<tr>
						<td colspan="5" class="table-title">
							<h4>������ ����</h4> 
						</td>
					</tr>
					<tr>
						<td>������ �̸�</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>������ ���̵�</td>
						<td>${user.userId}</td>
					</tr>
					<tr>
						<td>������ ����ó</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td colspan="5" class="table-title">
							<h4>��� ����</h4> 
						</td>
					</tr>
					<tr>
						<td>�ּ�</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>��� ��� ��¥</td>
						<td><input type="text"></td>
					</tr>
					<tr>
						<td>��û����</td>
						<td><textarea name="" id="" rows="5"></textarea></td>
					</tr>
					<tr>
						<td colspan="5" class="table-title">
							<h4>���� ����</h4> 
						</td>
					</tr>
					<tr>
						<td>���� �ݾ�</td>
						<td><span>0000</span></td>
					</tr>
					<tr>
						<td>���� ���</td>
						<td>
							<select name="" id="">
								<option value="1">���� ����</option>
								<option value="2">�ſ� ����</option>
							</select>
						</td>
					</tr>
					<!-- <tr>
						<td colspan="5" class="table-title">
							
						</td>
					</tr> -->
				</tbody>
			</table>
			<button type="button" class="buy">�����ϱ�</button>
		</c:if>
	</div>
</body>

</html>