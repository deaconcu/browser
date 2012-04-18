<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="top.jsp" %>    

<!DOCTYPE HTML>
<html>
  <%@ include file="head.jsp" %>    
  <body>
    <div id="page">
    <%@ include file="header.jsp" %>
      <div id="wrapper">
      </div>
        <div id="content">
                            管理请点击右边链接<br /><br /><br />
                            
          json接口：<br /><br />
          <a href="extension/list_cat.do">1. extension按类别列表</a><br /><br />
          <a href="appbox/get_all_json.do">2. appbox全部列表</a><br /><br />
          <a href="appbox/get_item_json.do?appboxItemIdString=17,19,21&lastUpdateTime=1334135171681">3. appbox按指定内容列表（示例）</a><br /><br />
        
        </div>
        <%@ include file="sidebar.jsp" %> 
      
      
      <%@ include file="footer.jsp" %> 
    </div>
    
  </body>
</html>
