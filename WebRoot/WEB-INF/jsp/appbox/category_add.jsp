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
            <div class="sign"><p>Appbox &gt; 新建分类</p></div>
            <div class="post">
		        <s:form action="add_cat" name="category" method="post" theme="simple">
		            <label>name: </label>
		            <s:textfield name="appboxCategory.name" cssClass="text-input"/> <br /><br />
		            <s:submit value="submit" cssClass="button"/>
		        </s:form>
            </div>
        </div>
      <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>