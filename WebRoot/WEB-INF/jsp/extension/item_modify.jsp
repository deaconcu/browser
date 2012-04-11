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
            <div class="sign"><p>Extension &gt; 修改</p></div>
            <div class="post">    
              <s:form action="modify_item" method="post" enctype="multipart/form-data" theme="simple">
	            <s:hidden name="item.id"/>
	            <s:hidden name="item.url"/>
	            <s:hidden name="item.iconUrl"/>
	            <s:hidden name="item.largeIconUrl"/>
	            <s:hidden name="item.postTime"/>
	            <label>name:</label> 
	              <s:textfield name="item.name" size="50" cssClass="text-input"/><br /><br />
	            <label>versionName:</label> 
	              <s:textfield name="item.versionName" size="50" cssClass="text-input"/><br /><br />
	            <label>version:</label> 
	              <s:textfield name="item.version" size="50" cssClass="text-input"/><br /><br />
	            <label>packageName:</label> 
	              <s:textfield name="item.packageName" size="50" cssClass="text-input"/><br /><br />
	            <label>mainPageUrl:</label> 
	              <s:textfield name="item.mainPageUrl" size="50" cssClass="text-input"/><br /><br />
	            <label>description:</label> 
                  <s:textfield name="item.description" size="50" cssClass="text-input"/><br /><br />
	            <label>file: <a href="<s:property value="item.url"/>">查看文件</a></label>
	              <s:file name="ext" size="70" cssClass="file-input"/><br /><br />
	            <label>iconImg: <a href="<s:property value="item.iconUrl"/>">查看文件</a></label>
	              <s:file name="icon" size="70" cssClass="file-input"/><br /><br />
	            <label>bigIconImg:
	              <s:if test="!item.largeIconUrl.equals(\"\")"><a href="<s:property value="item.largeIconUrl"/>">文件</a></s:if>
	            </label>
	              <s:file name="largeIcon" size="70" cssClass="file-input"/><br /><br />
	            <label>category:</label> 
	              <s:select name="item.category.id" value="item.category.id" list="categories" listKey="id" listValue="name"/><br /><br />
	            <s:submit label="submit" cssClass="button"/>
              </s:form>
        
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
