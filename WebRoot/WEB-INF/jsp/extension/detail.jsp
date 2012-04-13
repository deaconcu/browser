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
                <div class="sign"><p>Extension &gt; 详情</p></div>
                <div class="detail">
	                <table>
	                    <tr><td class="title">name:</td><td><s:property value="item.name" /></td></tr>
	                    <tr><td class="title">versionName:</td><td><s:property value="item.versionName" /></td></tr>
	                    
	                    <tr><td class="title">version:</td><td><s:property value="item.version" /></td></tr>
	                    <tr><td class="title">packageName:</td><td><s:property value="item.packageName" /></td></tr>
	                    <tr><td class="title">mainPageUrl:</td><td><s:property value="item.mainPageUrl" /></td></tr>
	                    <tr><td class="title">description:</td><td><s:property value="item.description" /></td></tr>
	                    <tr><td class="title">file:</td><td><a href="<s:property value="item.url" />">下载文件</a></td></tr>
	                    <tr><td class="title">iconImg:</td><td><img src="<s:property value="item.iconUrl" />" /></td></tr>
	                    <tr>
		                    <td class="title">bigIconImg:</td>
		                    <td>
			                    <s:if test="!item.largeIconUrl.equals(\"\")">
			                       <img src="<s:property value="item.largeIconUrl}"/>" />
			                    </s:if>
			                    <s:else>没有上传文件</s:else>
		                    </td>
	                    </tr>
	                    <tr><td class="title">category:</td><td><s:property value="item.category.name" /></td></tr>
	                    <tr><td class="title">postTime:</td><td><s:property value="item.date" /></td></tr>

				    </table>
				    <div class="modify">
                        <span><a href="extension/modify_item.do?itemId=<s:property value="item.id" />">修 改</a></span>
                    </div>
			    </div>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>