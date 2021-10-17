<%@page import="com.model2.mvc.service.domain.Product"%>
<%@page import="com.model2.mvc.service.domain.User"%>
<%@page import="com.model2.mvc.service.domain.Purchase"%>
<%@ page contentType="text/html; charset=euc-kr" %>

<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.common.*" %>

<%
List<Purchase> list = (List<Purchase>)request.getAttribute("list");

Search search=(Search)request.getAttribute("search");

String urlMenu = request.getParameter("menu");

Page resultPage=(Page)request.getAttribute("resultPage");

%>



<html>
    <head>
    <title>���� �����ȸ</title>
    
    <link rel="stylesheet" href="/css/admin.css" type="text/css">
    
    <script type="text/javascript">
        function fncGetPurchaseList(currentPage) {
        	document.getElementById("currentPage").value = currentPage;
            document.detailForm.submit();
        }
    </script>
    </head>
    
    <body bgcolor="#ffffff" text="#000000">
    
    <div style="width: 98%; margin-left: 10px;">
    
    <form name="detailForm" action="/listPurchase.do" method="post">
    
    <table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
        <tr>
            <td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
            <td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="93%" class="ct_ttl01">���� �����ȸ</td>
                    </tr>
                </table>
            </td>
            <td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
        </tr>
    </table>
    
    <table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
        <tr>
            <td colspan="11">��ü <%= resultPage.getTotalCount() %> �Ǽ�,	���� <%= resultPage.getCurrentPage() %> ������</td>
        </tr>
        <tr>
            <td class="ct_list_b" width="100">No</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b" width="150">ȸ��ID</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b" width="150">ȸ����</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">��ȭ��ȣ</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">�����Ȳ</td>
            <td class="ct_line02"></td>
            <td class="ct_list_b">��������</td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="808285" height="1"></td>
        </tr>
    
         <% 	
        if(list != null){
			int no=list.size();
			for(int i=0; i<list.size(); i++) {
				Purchase vo = (Purchase)list.get(i);
				User user = vo.getBuyer();
				Product product = vo.getPurchaseProd();
				System.out.println(vo);
		%>
        
        <tr class="ct_list_pop">
            <td align="center">
            	<% if(vo.getTranCode().equals("1")){ %>
						<a href="/getPurchase.do?tranNo=<%=vo.getTranNo()%>"><%=no--%></a>
					<% }else{%>
					<%=no--%>
				<%}%>
            </td>
            <td></td>
            <td align="left">
                <a href="/getUser.do?userId=<%=user.getUserId()%>"><%=user.getUserId()%></a>
            </td>
            <td></td>
            <td align="left"><%=vo.getReceiverName()%></td>
            <td></td>
            <td align="left"><%=vo.getReceiverPhone()%></td>
            <td></td>
            <td align="left">
            <%if(vo.getTranCode().equals("1")){%>
            	���� �Ϸ�� ���� �Դϴ�.
            <%}else if(vo.getTranCode().equals("2")){%>
            	����� �Դϴ�.
            <%}else{%>
            	���� Ȯ��.
            <%} %>
            </td>
            <td></td>
            <td align="left">
            <%if(vo.getTranCode().equals("2")){%>
            	<a href ="updateTranCode.do?prodNo=<%=vo.getPurchaseProd().getProdNo()%>"  onclick='return confirm("������ ���� �ϼ̽��ϱ� ?");'>���ǵ���</a>
            <%}%>
            </td>
        </tr>
        <tr>
            <td colspan="11" bgcolor="D6D7D6" height="1"></td>
        </tr>
        <% }
		} %>
        
        
    </table>
    
    <!-- PageNavigation Start... -->
	<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
		<tr>
			<td align="center">
			   <input type="hidden" id="currentPage" name="currentPage" value=""/>
				<% if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
						�� ����
				<% }else{ %>
						<a href="javascript:fncGetPurchaseList('<%=resultPage.getCurrentPage()-1%>')">�� ����</a>
				<% } %>
	
				<%	for(int i=resultPage.getBeginUnitPage();i<= resultPage.getEndUnitPage() ;i++){	%>
						<a href="javascript:fncGetPurchaseList('<%=i %>');"><%=i %></a>
				<% 	}  %>
		
				<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
						���� ��
				<% }else{ %>
						<a href="javascript:fncGetPurchaseList('<%=resultPage.getEndUnitPage()+1%>')">���� ��</a>
				<% } %>
			
	    	</td>
		</tr>
	</table>
	<!-- PageNavigation End... -->
    <!--  ������ Navigator �� -->
    </form>
    
    </div>
    
    </body>
</html>