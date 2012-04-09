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
            <div class="sign"><p>Extension > 修改分类</p></div>
            <div class="post">
		        <s:form action="modify_cat" method="post" theme="simple">
		            <s:hidden name="category.id"/>
		            <label>分类名:</label>
		            <s:textfield name="category.name" cssClass="text-input"/><br /><br />
		            <s:submit value="submit" cssClass="button"/>
		        
		        </s:form>
            </div>
        </div>
      <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>