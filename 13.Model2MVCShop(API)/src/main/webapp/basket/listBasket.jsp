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
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	
	
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
		table textarea{resize: none; border: 1px solid #9b9b9b; border-radius: 5px;}
		.buy{width: 50%; font-size: 2rem; padding: 7px 0 7px 0; margin: 0 auto 20px auto; display: block;}
		
	</style>

	<script type="text/javascript">
		$(document).ready(function() {
			getTotalPrice();
			$("#cbx_chkAll").click(function() {
				if($("#cbx_chkAll").is(":checked")) {
					$("input[name=chk]").prop("checked", true);
					$("#prod-table tbody").find("tr.product").addClass("select");
				}else {
					$("input[name=chk]").prop("checked", false);
					$("#prod-table tbody").find("tr.product").removeClass("select");
				};
				getTotalPrice();
			});
			
			$("input[name=chk]").click(function() {
				const total = $("input[name=chk]").length;
				const checked = $("input[name=chk]:checked").length;
				if($(this).closest("tr").hasClass("select")){
					$(this).closest("tr").removeClass("select");
				}else{
					$(this).closest("tr").addClass("select");
				}

				if(total != checked) {
					$("#cbx_chkAll").prop("checked", false)
				}else {
					$("#cbx_chkAll").prop("checked", true)
				}; 
				getTotalPrice();
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
				getTotalPrice();
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
				getTotalPrice();
			})

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

			// ���� üũ
			$("input[type='number']").on("change", function(){

				// console.log("��� =", prod_count, typeof prod_count , "���� ����", $(this).val(), typeof $(this).val())
				// console.log($(this).attr("max"));
				const prod_count = $(this).attr("max");
				const prod_price = $(this).attr("price");

				if(Number(prod_count )< Number($(this).val())){
					alert("������ �ʹ� �����ϴ�");
					$(this).val(prod_count);
				}
				const p_t_price = prod_price*Number($(this).val());
				const el_p_t_price = $(this).closest("tr").find(".prod-total-price");
				el_p_t_price.text(p_t_price+"��");
				el_p_t_price.attr("totalprice", p_t_price);
				// const total = 
				
				// console.log($("#prod-table").find(".prod-total-price").attr("totalprice"));
				// console.log($("#prod-table").find(".prod-total-price"));
				getTotalPrice();
				

			});
			// ����
			$("button.buy").on("click", ()=>{
				console.log("Ŭ��!");
				
				// console.log($("input:checkbox[name='chk']:checked").length !== 0 );
				if($("input:checkbox[name='chk']:checked").length !== 0){
					
					let prod_no_list = [];
					let prod_count_list = [];

					$("input:checkbox[name='chk']:checked").each(function() {
						prod_no_list.push($(this).val());
						// console.log($(this).closest('tr').find("input[type='number']").val());
						prod_count_list.push($(this).closest('tr').find("input[type='number']").val());
					});
					console.log(prod_count_list);
					console.log(prod_no_list);
					const reqData = JSON.stringify({
						"prodNo" : prod_no_list,
						"prodCount" : prod_count_list,
						"purchase" : $("#purchase-form").serializeObject()
					});
					console.log(reqData);

					$.ajax({
						type  : "POST",
						url    : "/basket/json/addBuyBasket",
						data : reqData,
						dataType : "json" ,
						contentType: "application/json; charset=utf-8",
						success: function(result){
							console.log(result);
							alert(result+"���� ��ǰ�� ���� �Ǿ����ϴ�.");
							window.close();
						},
						error: function(xhr, status, error) {
							alert(error);
							
						}  
					});

				}else{
					alert("��ǰ�� �������ּ���");
				}
				//serialize()
				// console.log($("#purchase-form").serialize());
				// console.log($("#purchase-form").serializeArray());
				// console.log(JSON.stringify($("#purchase-form")));
				// console.log($("#purchase-form").serializeObject());

				

			})
			
			jQuery.fn.serializeObject = function() { 
				var obj = null; 
				try { 
					if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) { 
						var arr = this.serializeArray(); 
						if(arr){ 
							obj = {}; 
							jQuery.each(arr, function() { 
								obj[this.name] = this.value; 
							});
						} 
					} 
				}catch(e) { 
					alert(e.message); 
				}finally {} 
				return obj; 
			};

			function getTotalPrice() {
				let total_price = 0;
				$("#prod-table tr.select").find(".prod-total-price").each((i,el)=>{
					total_price+= Number($(el).attr("totalprice"));
				})
				console.log(total_price);
				$("#total-prod-price").text(total_price);
				$(".total-price").text(total_price+2500 +"��");
				$("input[name='totalPrice']").val(total_price+2500);

			}
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
			<table id ="prod-table" class="table table-hover table-striped">
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
						<tr class="product">
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
										<div >
											��� : ${product.prodCount}
										</div>
									</div>
								</div>
							</td>
							<td class="prod-total-price" totalprice ="${product.price}">
									${product.price}��
							</td>
							<td>
								<div>
									<input type="number" style="width: 40px;" value="1"  min="1" max="${product.prodCount}" price="${product.price}">
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
										<span id="total-prod-price">0</span>
									</div>
									<div> + </div>
									<div>
										<span>��ۺ�</span>
										<br>
										<span id="delivery-price">2500</span>
									</div>
									<div> = </div>
									<div>
										<span>�ֹ��ݾ�</span>
										<br>
										<span class="total-price">0</span>
									</div>
								</div>
							</div>
						</td>
					</tr>
					
				</tbody>
				
			</table>
			<form name ="detailForm" id="purchase-form">
				<table class="table table-bordered">
					<tbody>
						
						<tr>
							<td colspan="5" class="table-title">
								<h4>������ ����</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label for="receiverName">������ �̸�</label>
							</td>
							<td><input type="text" id="receiverName" name="receiverName" value="${user.userName}"></td>
						</tr>
						<tr>
							<td>
								<label >������ ���̵�</label>
							</td>
							<td>
								<input type="hidden" id="userId" name="userId"value="${user.userId}">
								${user.userId}
							</td>
						</tr>
						<tr>
							<td>
								<label for="receiverPhone">������ ����ó</label>
							</td>
							<td><input type="text" id="receiverPhone" name="receiverPhone"value="${user.phone}"></td>
						</tr>
						<tr>
							<td colspan="5" class="table-title">
								<h4>��� ����</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label for="divyAddr">�ּ�</label>
							</td>
							<td>
								<input type="text"id="divyAddr" name="divyAddr"value="${user.addr}">
								<br>
								<input type="text"id="divyAddrDetail" name="divyAddrDetail"value="${user.addrDetail}">
							</td>
						</tr>
						<tr>
							<td>
								<label for="divyDate" >��� ��� ��¥</label>
							</td>
							<td><input type="text" id="divyDate" name="divyDate" placeholder="��� ��� ��¥"></td>
						</tr>
						<tr>
							<td>
								<label for="divyRequest">��û ����</label>
							</td>
							<td><textarea name="divyRequest" rows="5" placeholder="��� �� ��û ������ �Է����ּ���."></textarea></td>
						</tr>
						<tr>
							<td colspan="5" class="table-title">
								<h4>���� ����</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label>���� �ݾ�</label>
							</td>
							<td>
								<span class="total-price"></span>
								<input type="hidden" name="totalPrice" value="">
							</td>
						</tr>
						<tr>
							<td>
								<label for="paymentOption">���� ���</label>
							</td>
							<td>
								<select name="paymentOption">
									<option value="1" selected="selected">���� ����</option>
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
			</form>
		</c:if>
	</div>

	<!-- daum API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="../javascript/daumAPI.js"></script>
	
</body>

</html>