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
    <!-- test git -->
    <body>
        <s:property value="actionErrors[0]"  />
        
        
        <script type="text/javascript">
            setTimeout("window.location='javascript:history.go(-1)'", 3000);  
        </script>
        <br /><br /><br />3<s:text name="redirect.in.seconds" /> 
        <a href="javascript:history.go(-1);"><s:text name="click.to.return" /></a>
    </body>
</html>
