<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	<title>장바구니</title>
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
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
						alert("선택하신 상품 "+result+"개가 삭제되었습니다.");
					},
					error: function(xhr, status, error) {
						alert("상품을 선택해 주세요.");
						
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
						alert("선택하신 상품 "+result+"개가 삭제되었습니다.");
						location.reload();
					},
					error: function(xhr, status, error) {
						alert(error);
						
					}  
				});
				getTotalPrice();
			})

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

			// 수량 체크
			$("input[type='number']").on("change", function(){

				// console.log("재고 =", prod_count, typeof prod_count , "구매 수량", $(this).val(), typeof $(this).val())
				// console.log($(this).attr("max"));
				const prod_count = $(this).attr("max");
				const prod_price = $(this).attr("price");

				if(Number(prod_count )< Number($(this).val())){
					alert("수량이 너무 많습니다");
					$(this).val(prod_count);
				}
				const p_t_price = prod_price*Number($(this).val());
				const el_p_t_price = $(this).closest("tr").find(".prod-total-price");
				el_p_t_price.text(p_t_price+"원");
				el_p_t_price.attr("totalprice", p_t_price);
				// const total = 
				
				// console.log($("#prod-table").find(".prod-total-price").attr("totalprice"));
				// console.log($("#prod-table").find(".prod-total-price"));
				getTotalPrice();
				

			});
			// 결제
			$("button.buy").on("click", ()=>{
				console.log("클릭!");
				
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
							alert(result+"개의 상품이 구매 되었습니다.");
							window.close();
						},
						error: function(xhr, status, error) {
							alert(error);
							
						}  
					});

				}else{
					alert("상품을 선택해주세요");
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
				$(".total-price").text(total_price+2500 +"원");
				$("input[name='totalPrice']").val(total_price+2500);

			}
		});

	</script>
</head>

<body>

	<div id="user-id" class="container" data-user="${user.userId}" >
		<h2>장바구니</h2>
		<c:if test="${empty list}">
			장바구니 목록이 비었습니다.
		</c:if>
		<c:if test="${!empty list}">
			<table id ="prod-table" class="table table-hover table-striped">
				<thead>
					<tr>
						<td>
							<input type="checkbox" id="cbx_chkAll">
							전체선택
						</td>
						<td>상품정보</td>
						<td>상품금액</td>
						<td>상품수량</td>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="5">판매자 배송 상품</td>
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
											재고 : ${product.prodCount}
										</div>
									</div>
								</div>
							</td>
							<td class="prod-total-price" totalprice ="${product.price}">
									${product.price}원
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
									<button type="button" id="all-delete">전체삭제</button>
									<button type="button" id="select-delete">선택삭제</button>
								</div>
								<div class="table-flex price-box">
									<div>
										<span>상품가격</span>
										<br>
										<span id="total-prod-price">0</span>
									</div>
									<div> + </div>
									<div>
										<span>배송비</span>
										<br>
										<span id="delivery-price">2500</span>
									</div>
									<div> = </div>
									<div>
										<span>주문금액</span>
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
								<h4>구매자 정보</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label for="receiverName">구매자 이름</label>
							</td>
							<td><input type="text" id="receiverName" name="receiverName" value="${user.userName}"></td>
						</tr>
						<tr>
							<td>
								<label >구매자 아이디</label>
							</td>
							<td>
								<input type="hidden" id="userId" name="userId"value="${user.userId}">
								${user.userId}
							</td>
						</tr>
						<tr>
							<td>
								<label for="receiverPhone">구매자 연락처</label>
							</td>
							<td><input type="text" id="receiverPhone" name="receiverPhone"value="${user.phone}"></td>
						</tr>
						<tr>
							<td colspan="5" class="table-title">
								<h4>배송 정보</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label for="divyAddr">주소</label>
							</td>
							<td>
								<input type="text"id="divyAddr" name="divyAddr"value="${user.addr}">
								<br>
								<input type="text"id="divyAddrDetail" name="divyAddrDetail"value="${user.addrDetail}">
							</td>
						</tr>
						<tr>
							<td>
								<label for="divyDate" >배송 희망 날짜</label>
							</td>
							<td><input type="text" id="divyDate" name="divyDate" placeholder="배송 희망 날짜"></td>
						</tr>
						<tr>
							<td>
								<label for="divyRequest">요청 사항</label>
							</td>
							<td><textarea name="divyRequest" rows="5" placeholder="배송 시 요청 사항을 입력해주세요."></textarea></td>
						</tr>
						<tr>
							<td colspan="5" class="table-title">
								<h4>결제 정보</h4> 
							</td>
						</tr>
						<tr>
							<td>
								<label>결제 금액</label>
							</td>
							<td>
								<span class="total-price"></span>
								<input type="hidden" name="totalPrice" value="">
							</td>
						</tr>
						<tr>
							<td>
								<label for="paymentOption">결제 방법</label>
							</td>
							<td>
								<select name="paymentOption">
									<option value="1" selected="selected">현금 구매</option>
									<option value="2">신용 구매</option>
								</select>
							</td>
						</tr>
						<!-- <tr>
							<td colspan="5" class="table-title">
								
							</td>
						</tr> -->
					</tbody>
				</table>
				<button type="button" class="buy">결제하기</button>
			</form>
		</c:if>
	</div>

	<!-- daum API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="../javascript/daumAPI.js"></script>
	
</body>

</html>