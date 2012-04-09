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
			  <form action="extension/add_item.do" name="item" method="post" enctype="multipart/form-data">
				  <label>name:</label> <input type="text" name="item.name" class="text-input"/> <br /><br />
				  <label>versionName:</label> <input type="text" name="item.versionName" class="text-input"/> <br /><br />
			      <label>version:</label> <input type="text" name="item.version" class="text-input"/> <br /><br />
				  <label>mainPageUrl:</label> <input type="text" name="item.mainPageUrl" class="text-input"/> <br /><br />
				  <label>packageName:</label> <input type="text" name="item.packageName" class="text-input"/> <br /><br />
				  <label>file:</label> <input type="file" name="ext" class="file-input" size="60" /> <br /><br />
				  <label>iconImg:</label> <input type="file" name="icon" class="file-input" size="60"/> <br /><br />
				  <label>bigIconImg:</label> <input type="file" name="bigIconImg" class="file-input" size="60"/> <br /><br />
				  <label>description:</label> <input type="text" name="item.description" class="text-input"/> <br /><br />
				  <label>category:</label> <input type="text" name="item.category.id" class="text-input"/> <br /><br />
				  <input type="submit" value="提 交" class="button"/>
			  </form>
	        </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
