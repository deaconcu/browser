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
                <div class="sign"><p>Appbox &gt; 新建分类</p></div>
		        <s:form action="add_cat" name="category" method="post" theme="simple" enctype="multipart/form-data">
		            <label>name: </label>
		            <s:textfield name="appboxCategory.name" cssClass="text-input"/> <br /><br />
		            <label>上传图片</label>
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