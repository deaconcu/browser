<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
        <form action="add_cat.do" name="category" method="post">
            name: <input type="text" name="category.name" /> <br /><br />
            <input type="submit" value="submit" />
        </form>
    </body>
</html>
