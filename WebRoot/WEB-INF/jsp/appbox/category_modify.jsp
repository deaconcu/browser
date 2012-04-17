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
            
            <div class="post">
                <div class="sign"><p>Appbox &gt; 修改分类</p></div>
		        <s:form action="modify_cat" method="post" theme="simple" enctype="multipart/form-data">
		            <s:hidden name="appboxCategory.id"/> 
		            <s:hidden name="appboxCategory.root"/>
		            <s:hidden name="appboxCategory.postTime"/>
		            <s:hidden name="appboxCategory.img"/>
		            <label>分类名:</label>
		            <s:textfield name="appboxCategory.name" cssClass="text-input"/><br /><br />
		            <label>图片: <br /><br /><img src="<s:property value="appboxCategory.img"/>" /></label>
		            <s:file name="img" size="70" cssClass="file-input" /> <br /><br />
		            <s:submit value="submit" cssClass="button"/>
		        
		        </s:form>
            </div>
        </div>
      <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
