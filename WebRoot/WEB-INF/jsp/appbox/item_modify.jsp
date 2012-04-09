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
    
        <s:form action="modify_item" method="post" theme="simple">
            <s:hidden name="appboxItem.id"/>
            name: <s:textfield name="appboxItem.name" size="50"/> <br /><br />
            source: <s:textfield name="appboxItem.source" size="50"/> <br /><br />
            titelRegex: <s:textfield name="appboxItem.titleRegex" size="50"/> <br /><br />
            urlRegex: <s:textfield name="appboxItem.urlRegex" size="50"/> <br /><br />
            picRegex: <s:textfield name="appboxItem.picRegex" size="50"/> <br /><br />
            category: <s:select name="appboxItem.appboxCategory.id" value="appboxItem.appboxCategory.id" list="listCategory" listKey="id" 
               listValue="name" /><br /><br />
            
            <s:submit label="submit" />
        </s:form>
   
    </body>
</html>
