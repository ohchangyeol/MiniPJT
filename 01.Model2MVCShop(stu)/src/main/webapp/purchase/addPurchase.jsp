<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>

<%
System.out.println("====================addPurchaseView ����");
PurchaseVO purchase = (PurchaseVO)request.getAttribute("purchase");
%>

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
		<td><%=purchase.getPurchaseProd().getProdNo()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td><%=purchase.getBuyer().getUserId()%></td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		   	<%if(purchase.getPaymentOption() == "1"){%> 
		   	���ݱ���
		   	<%}else{%>
		    �ſ뱸��
			<%}%>
		
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%=purchase.getReceiverName()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%=purchase.getReceiverPhone()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%=purchase.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%=purchase.getDivyRequest()%></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%=purchase.getDivyDate()%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>