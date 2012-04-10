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
        <s:property value="actionErrors[0]" />
        
        
        <script language="javascript" type="text/javascript">
            setTimeout("window.location='javascript:history.go(-1)'", 3000);  
        </script>
        <br /><br /><br />3秒后自动返回，或<a href="javascript:history.go(-1);">点击返回</a>
    </body>
</html>
