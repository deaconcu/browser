<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
        <s:property value="actionErrors[0]" />，
        1秒后自动跳转 ... ...
        <script type="text/javascript">
            setTimeout("window.location='javascript:history.go(-1)'", 1500);
        </script>
    </body>
</html>