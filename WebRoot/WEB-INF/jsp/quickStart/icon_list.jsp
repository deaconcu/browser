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
                <div class="sign"><p>QuickStart &gt; 列表</p></div>
                <div class="add">
                    <span><a href="quickStart/add_icon.do"> + 添加</a></span>
                </div>
                <div class="pages">
	                <s:if test="page > 1">
	                    <a href="quickStart/get_icon_list.do?page=<s:property value="page - 1" />">上一页</a>
	                </s:if>
	                <s:if test="page > 1 && iconList.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
	                <s:if test="iconList.size() > 0">
	                   <a href="quickStart/get_icon_list.do?page=<s:property value="page + 1" />">下一页</a>
	                </s:if>
                </div>
                <table>
		        <s:iterator value="iconList">
		            <tr>
		              <td class="name"><p>
		                  <span><a href="quickStart/get_icon.do?iconId=<s:property value="id" />"><s:property value="webUrl" /></a></span>
		                  
		                  </p>
		              </td>
		              <td class="op">
			              <p>
	                           <a href="quickStart/get_icon.do?iconId=<s:property value="id" />">详细</a> | 
	                           <a href="quickStart/modify_icon.do?iconId=<s:property value="id" />">修改</a> | 
	                           <a href="quickStart/delete_icon.do?iconId=<s:property value="id" />">删除</a>
	                      </p>
                      </td>
		            </tr>
		        </s:iterator>
		        </table>
		        <div class="pages">
	                <s:if test="page > 1">
	                    <a href="quickStart/get_icon_list.do?page=<s:property value="page - 1" />">上一页</a>
	                </s:if>
	                <s:if test="page > 1 && iconList.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
	                <s:if test="iconList.size() > 0">
	                   <a href="quickStart/get_icon_list.do?page=<s:property value="page + 1" />">下一页</a>
	                </s:if>
                </div>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
