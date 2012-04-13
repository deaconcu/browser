<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../top.jsp" %>    

<!DOCTYPE HTML>
<html>
  <%@ include file="../head.jsp" %>    
  <body>
    <div id="page">
      <%@ include file="../header.jsp" %>
      <div id="wrapper">
      </div>
      <div id="content">
      	<div class="sign"><p>QuickStart &gt; 新建</p></div>
      	<div class="post">
      		<s:form action="add_icon" method="post" enctype="multipart/form-data" theme="simple">
      			<label>web url:</label>
      			<s:textfield name="quickStartIcon.webUrl" cssClass="text-input"></s:textfield> <br /><br />
      			<label>icon file</label>
      			<s:file name="icon" cssClass="file-input"></s:file>
      			<s:submit value="submit" cssClass="button"/>
      		</s:form>
      	</div>
      </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
