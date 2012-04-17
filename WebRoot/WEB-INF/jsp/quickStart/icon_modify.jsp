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
            <div class="sign"><p>QuickStart &gt; 修改</p></div>
            <div class="post">    
              <s:form action="modify_icon" method="post" enctype="multipart/form-data" theme="simple">
              	<s:hidden name="icon.id"></s:hidden>
              	<s:hidden name="icon.webUrl"></s:hidden>
              	<s:hidden name="icon.imgUrl"></s:hidden>
              	<label>web url:</label>
      			<s:textfield name="quickStartIcon.webUrl" cssClass="text-input"></s:textfield> <br /><br />
              	<label>icon:<a href="<s:property value="quickStartIcon.imgUrl"/>">查看文件</a></label>
              	<s:file name="icon" cssClass="file-input"></s:file>
              	<s:submit label="submit" cssClass="button"/>
              </s:form>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
