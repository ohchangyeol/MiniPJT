<%--
<%@page import="com.model2.mvc.service.domain.Purchase"%> 
--%>
<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
System.out.println("====================addPurchaseView ����");
Purchase purchase = (Purchase)request.getAttribute("purchase");
--%>

<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=0" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<%-- <td><%=purchase.getPurchaseProd().getProdNo()%></td> --%>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<%-- <td><%=purchase.getBuyer().getUserId()%></td> --%>
		<td>${purchase.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		   	<%-- <%if(purchase.getPaymentOption() == "1"){%> 
		   	���ݱ���
		   	<%}else{%>
		    �ſ뱸��
			<%}%> --%>
			<c:choose>
				<c:when test="${purchase.paymentOption == 1}">���ݱ���</c:when>
				<c:otherwise>�ſ뱸��</c:otherwise>
			</c:choose>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<%-- <td><%=purchase.getReceiverName()%></td> --%>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<%-- <td><%=purchase.getReceiverPhone()%></td> --%>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<%-- <td><%=purchase.getDivyAddr()%></td> --%>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<%-- <td><%=purchase.getDivyRequest()%></td> --%>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<%-- <td><%=purchase.getDivyDate()%></td> --%>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>