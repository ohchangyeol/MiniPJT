<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>��� ��ǰ ����</title>
</head>

<body>
	����� ��� ��ǰ�� �˰� �ִ�
<br>
<br>
<%--
<%
	String history = null;
	
	Cookie[] cookies = request.getCookies();
	
	if (cookies!=null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("history")) {
				history = cookie.getValue();
			}
		}
		if (history != null) {
			String[] h = history.split(",");
			for (int i = 0; i < h.length; i++) {
				if (!h[i].equals("null")) {
%>
	<a href="/getProduct.do?prodNo=<%=h[i]%>&menu=search"	target="rightFrame"><%=h[i]%></a>
<br>
<% 	
				}
			}
		}
	}
%>
--%>
	<c:set var="productProdNo" value="${cookie.history.value}"/>
	<%-- <c:out value="${productProdNo}"/> --%>
	<c:forTokens var="list" items="${productProdNo}" delims=","> 
	<a href="/getProduct.do?prodNo=${list}&menu=search"	target="rightFrame">${list}</a><br/>
	</c:forTokens>

</body>
</html>