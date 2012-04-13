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
                <div class="sign"><p>Appbox &gt; 修改</p></div>
		        <s:form action="modify_item" method="post" theme="simple">
		            <s:hidden name="appboxItem.id"/>
		            <s:hidden name="appboxItem.postTime"/>
		            <s:hidden name="appboxItem.matchTime"/>
		            <s:hidden name="appboxItem.matchStatue"/>
		            <label>name: </label> 
		              <s:textfield name="appboxItem.name" cssClass="text-input"/> <br /><br />
		            <label>source: </label> 
		              <s:textfield name="appboxItem.source" cssClass="text-input"/> <br /><br />
		            <label>sourceCharSet:</label> 
                      <s:textfield name="appboxItem.charSet" cssClass="text-input"/> <br /><br />
		            <label>titelRegex:</label>  
		              <s:textfield name="appboxItem.titleRegex" cssClass="text-input"/> <br /><br />
		            <label>urlRegex: </label> 
		              <s:textfield name="appboxItem.urlRegex" cssClass="text-input"/> <br /><br />
		            <label>picRegex: </label> 
		              <s:textfield name="appboxItem.picRegex" cssClass="text-input"/> <br /><br />
		            <label>category: </label> 
		              <s:select name="appboxItem.appboxCategory.id" value="appboxItem.appboxCategory.id" 
		                 list="listCategory" listKey="id" listValue="name" /><br /><br />
		            
		            <s:submit value="submit" cssClass="button"/>
		        </s:form>
            </div>
        </div>
      <%@ include file="../sidebar.jsp" %> 
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>