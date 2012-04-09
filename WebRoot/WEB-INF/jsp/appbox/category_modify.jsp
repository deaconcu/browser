<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
    <head>
        <base href="<%=basePath%>">
        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
    </head>
    
    <body>
    
        <s:form action="appbox/modify_cat.do" method="post" theme="simple">
            <s:hidden name="appboxCategory.id"/> 
            <s:hidden name="appboxCategory.root"/>
            categoryName: <s:textfield name="appboxCategory.name" size="50"/><br /><br />
            <s:submit label="submit" />
        
        </s:form>
        
        
    </body>
</html>
