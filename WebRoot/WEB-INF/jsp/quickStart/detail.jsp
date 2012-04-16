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
                <div class="sign"><p>QuickStart &gt; 详情</p></div>
                <div class="detail">
	                <table>
	                	<tr><td class="title">web url:</td><td><s:property value="quickStartIcon.webUrl" /></td></tr>
	                    
	                    <tr>
		                    <td class="title">Icon:</td>
		                    <td>
			                    <s:if test="!quickStartIcon.iconUrl.equals(\"\")">
			                       <img src="<s:property value="quickStartIcon.imgUrl"/>" />
			                    </s:if>
			                    <s:else>没有上传文件</s:else>
		                    </td>
	                    </tr>

				    </table>
				    <div class="modify">
                        <span><a href="quickStart/modify_icon.do?iconId=<s:property value="quickStartIcon.id" />">修 改</a></span>
                    </div>
			    </div>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>