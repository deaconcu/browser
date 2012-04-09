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
	        <div class="sign"><p>Extension > 新建</p></div>
		    <div class="post">    
			  <s:form action="add_item" method="post" enctype="multipart/form-data" theme="simple">
				  <label>name:</label> 
                    <s:textfield name="item.name" cssClass="text-input"/><br /><br />
				  <label>versionName:</label> 
                    <s:textfield name="item.versionName" cssClass="text-input"/><br /><br />
			      <label>version:</label> 
                    <s:textfield name="item.version" cssClass="text-input"/><br /><br />
			      <label>packageName:</label> 
                    <s:textfield name="item.packageName" cssClass="text-input"/><br /><br />
				  <label>mainPageUrl:</label> 
                    <s:textfield name="item.mainPageUrl" cssClass="text-input"/><br /><br /> 
				  <label>file:</label> 
				    <s:file name="ext" size="70" cssClass="file-input"/><br /><br />
				  <label>iconImg:</label>
				    <s:file name="icon" size="70" cssClass="file-input"/><br /><br />
				  <label>bigIconImg:</label> 
				    <s:file name="largeIcon" size="70" cssClass="file-input"/><br /><br />
				  <label>description:</label> 
				    <s:textfield name="item.description" cssClass="text-input"/><br /><br />
				  <label>category:</label> 
				    <s:select name="item.category.id" value="item.category.id" list="categories" listKey="id" listValue="name"/><br /><br /> 
				  <s:submit value="submit" cssClass="button"/>
			  </s:form>
	        </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
