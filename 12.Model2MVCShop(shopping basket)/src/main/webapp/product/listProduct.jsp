<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 
 
<html>
<head>
	<c:choose>
		<c:when test="${param.menu == 'search'}">
			<title>상품 목록 조회</title>	
		</c:when>
		<c:otherwise>
			<title>상품 관리</title>
		</c:otherwise>
	</c:choose>
	<meta charset="EUC-KR">
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	
	
	<style>
        body {padding-top : 50px;}
        .product-back{width:100%; height: 500px;}
        .carousel-inner{margin-bottom:50px}
        .product-item-title{margin-bottom:50px;}
        .product-item-title span{font-size:30px;border-bottom:solid 2px}
        .carousel-caption p {margin-bottom:50px;}
        .col-lg-4 .product-price{font-size:12px;}
        .col-lg-4{margin-bottom:70px}
        .img-circle{filter: drop-shadow(5px 5px 5px #cccccc) }
   	</style>
   	
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
 	<link href="/css/font.css" rel="stylesheet">
 	<script type="text/javascript">
		$(function(){
				
			var isFlag = false;
			var page = 1;
			var menu = $("body").data("menu");
			
			//console.log("시발");
			$(window).scroll(function(){
		       if(window.innerHeight+ window.scrollY == $(document).height() && !isFlag ){
		     	  page++;
		     	  $.ajax({
					url : "/product/json/listProduct" ,
					method : "GET" ,
					data:{"currentPage" : page},
					dataType : "json" ,
					headers : {
						"Accept" : "application/json",
						"Content-Type" : "application/json"
					},
					success : function(JSONData , status) {
						var list = JSONData.list;
						console.log(JSONData);
						
						console.log(list);
						if(list.length == 0){
							console.log("list가 없어서 씀")
							isFlag = true;
						}
						var temp = "<div class='row' style='text-align: center;'>";
						for(var i =0 ; i < list.length ; i ++){
							console.log("for문 " + i);
							temp +=  "<div class='col-lg-4'>"
			                    +"<img class='img-circle' src='/images/uploadFiles/"+list[i].fileName+"' data-toggle='tooltip' data-placement='top' title='상품 번호 : "+list[i].prodNo+"<br>상품명 : "+list[i].prodName+"<br>상세 정보 : "+list[i].prodDetail+"<br>제조일자 : "+list[i].regDate+" <br>가격 : "+list[i].price+"원 <br>등록일자 : "+list[i].manuDate+"' width='140' height='140' data-html='true'>"
			                    +"<h2>"+list[i].prodName+"</h2>"
			                    +"<p class ='product-price'>"+list[i].price+"원</p>"
			                    +"<p>"+list[i].prodDetail+"</p>";
				                   
							if (menu == "search") {
		                        if( list[i].prodTranCode == null){
		                        	temp += "<button type='button' class='btn btn-primary' data-prodno='"+list[i].prodNo+"'>Buy &raquo;</button>";
		                        }else{
		                        	temp += "<button type='button' class='btn btn-danger'>sold out</button>";
		                        }
		                    }else if(menu == "manage"){
		                        if( list[i].prodTranCode == null){
		                        	temp += "<p><a class='btn btn-default' href='/product/updateProduct?prodNo="+list[i].prodNo+"' role='button'>Change &raquo;</a></p>";
		                        }else{
		                            if(list[i].prodTranCode .trim() == '1'){
// 		                            	temp += "<a class='btn btn-info' role='button' href='/purchase/updateTranCodeByProd?prodNo="+list[i].prodNo+"&menu="+menu+"' onclick='return confirm('선택 하신 상품 "+list[i].prodName+"을 배송 하시겠습니까?')'>배송하기</a>";
		                            }else if(list[i].prodTranCode .trim() == '2'){
		                            	temp+= "<a class='btn btn-danger' role='button' href='#'>배송중</a>";
		                            }else{
		                            	temp += "<a class='btn btn-danger' role='button' href='#'>재고 없음</a>";
		                            }
		                        }
		                    }
							temp += "</div>";
						}
						temp += "</div>";
						$(".marketing").append(temp);
					 }
				});
		       }//end of If
		     });
		    $('[data-toggle="tooltip"]').tooltip({
		      content: function() {
		         return $(this).prop('title');
		        }
		     }); 
			//console.log($(".btn[data-prodno]"));
			$(document).on("click",".btn[data-prodno]",function(e){
				console.log(this);
				var btn = $(this);
				location.href="/purchase/addPurchase?prod_no="+btn.data("prodno");
			});
		})
 	</script>
</head>

<body bgcolor="#ffffff" text="#000000" data-menu="${param.menu}">

	<jsp:include page="/layout/toolbar.jsp" />
	
	<!--  -->
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
        <div  class="first-slide product-back" style="background-color: #778899	;"></div>
          
          <div class="container">
            <div class="carousel-caption">
              <h1>Example headline.</h1>
              <p>이미지 없음 ㅠ ㅠ</p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">Sign up today</a></p> -->
            </div>
          </div>
        </div>
        <div class="item">
          <div  class="first-slide product-back" style="background-color: #465563;"></div>
          <div class="container">
            <div class="carousel-caption">
              <h1>여기두 없음ㅠ ㅠ</h1>
              <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">Learn more</a></p> -->
            </div>
          </div>
        </div>
        <div class="item">
          <div  class="first-slide product-back" style="background-color: #29333d;"></div>
          <div class="container">
            <div class="carousel-caption">
              <h1>ㅇㅅㅇ</h1>
              <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
              <!-- <p><a class="btn btn-lg btn-primary" href="#" role="button">Browse gallery</a></p> -->
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
   </div>
   <!--  -->
   <div class="container marketing">
   		<div class="row">
		  <div class="col-lg-8">
		    <div class="product-item-title">
			    <c:if test="${empty user}">
			        <span>Product</span>
			    </c:if>
		    
				<c:if test="${param.menu == 'search'}">
					<span>Product</span>
				</c:if>
				<c:if test="${param.menu == 'manage'}">
					<span>판매 상품 관리</span>
				</c:if>	
			</div>
		  </div><!-- /.col-lg-6 -->
		  <div class="col-lg-4">
		  <form name="detailForm" action="/product/listProduct?menu=${param.menu}" method="post">
		    <div class="input-group">
		      <input type="text" class="form-control" name="searchKeyword" placeholder="Search"value="${! empty search.searchKeyword ? search.searchKeyword : ""}">
		      <input type="hidden" name="searchCondition" value="0">
		      <span class="input-group-btn">
		        <button class="btn btn-default" type="submit">ㄱㄱ</button>
		      </span>
		    </div><!-- /input-group -->
		    </form>
		  </div><!-- /.col-lg-6 -->
		</div>
		
      <!-- Three columns of text below the carousel -->
      <div class="row" style="text-align: center;">
     	 <c:forEach var ="product" items="${list}">
	        <div class="col-lg-4">
	          <img class="img-circle" src="/images/uploadFiles/${product.fileName}" data-toggle="tooltip" data-placement="top" title="상품 번호 : ${product.prodNo}<br>상품명 : ${product.prodName}<br>상세 정보 : ${product.prodDetail}<br>제조일자 : ${product.regDate} <br>가격 : ${product.price}원 <br>등록일자 : ${product.manuDate}
	          " width="140" height="140" data-html="true">
	          <h2>${product.prodName}</h2>
	          <p class ="product-price">${product.price} 원</p>
	          <p>${product.prodDetail}</p>
	          <c:if test="${param.menu == 'search'}">
		        <c:if test="${empty product.prodTranCode }">
		          	<%-- <p><a class="btn btn-default" href="/purchase/addPurchase?prod_no=${product.prodNo}" role="button">Buy &raquo;</a></p> --%>
		          	<button type="button" class="btn btn-primary" data-prodno="${product.prodNo}">Buy &raquo;</button>
	          	</c:if>
	          	<c:if test="${!empty product.prodTranCode }">
		          	<button type="button" class="btn btn-danger">sold out</button>
	          	</c:if>
	          </c:if>
	          
	          <c:if test="${param.menu == 'manage'}">
		          <c:if test="${empty product.prodTranCode}">
		          	<p><a class="btn btn-default" href="/product/updateProduct?prodNo=${product.prodNo}" role="button">Change &raquo;</a></p>
		          </c:if>
		          <c:if test="${!empty product.prodTranCode}">
		          
		          	<c:choose>
						<c:when test="${product.prodTranCode == '1  '}">
							<a class="btn btn-info" role="button" href="/purchase/updateTranCodeByProd?prodNo=${product.prodNo}&menu=${param.menu}" onclick="return confirm('선택 하신 상품 ${product.prodName}을 배송 하시겠습니까?')">배송하기</a>
						</c:when>
						<c:when test="${product.prodTranCode == '2  '}">
							<a class="btn btn-danger" role="button" href="#">배송중</a>
						</c:when>
						<c:otherwise>
							<a class="btn btn-danger" role="button" href="#">재고 없음</a>
						</c:otherwise>
					</c:choose>
		          </c:if>
	          </c:if>
        </div><!-- /.col-lg-4 -->
      </c:forEach>
      <!-- <div class="row" style="text-align: center;">
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>/.col-lg-4
        
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>/.col-lg-4
        <div class="col-lg-4">
          <img class="img-circle" src="data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==" alt="Generic placeholder image" width="140" height="140">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>/.col-lg-4 -->
      </div><!-- /.row -->
   </div>
   
</body>
</html>