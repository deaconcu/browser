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
        <s:property value="actionMessages[0]" />，
        1秒后自动跳转 ... ...
        <script language="javascript" type="text/javascript">
            setTimeout("window.location='<s:property value="url" />'", 1500);  
        </script>
        
        <br /><br /><br />
        url: <s:property value="url" />       
    </body>
</html>
