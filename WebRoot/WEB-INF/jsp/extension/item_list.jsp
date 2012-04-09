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
                <div class="sign"><p>Extension > 列表</p></div>
                <div class="add">
                    <span><a href="extension/add_item.do"> + 添加</a></span>
                </div>
                <div class="pages">
	                <s:if test="page > 1">
	                    <a href="extension/get_item_list.do?page=<s:property value="page - 1" />">上一页</a>
	                </s:if>
	                <s:if test="page > 1 && itemList.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
	                <s:if test="itemList.size() > 0">
	                   <a href="extension/get_item_list.do?page=<s:property value="page + 1" />">下一页</a>
	                </s:if>
                </div>
                <table>
		        <s:iterator value="itemList">
		            <tr>
		              <td class="name"><p>
		                  <span><a href="extension/get_item.do?itemId=<s:property value="id" />"><s:property value="name" /></a></span>
		                  <s:property value="description" /> -- 
		                  <s:property value="category.name" /></p>
		              </td>
		              <td class="op">
			              <p>
	                           <a href="extension/get_item.do?itemId=<s:property value="id" />">详细</a> | 
	                           <a href="extension/modify_item.do?itemId=<s:property value="id" />">修改</a> | 
	                           <a href="extension/delete_item.do?itemId=<s:property value="id" />">删除</a>
	                      </p>
                      </td>
		            </tr>
		        </s:iterator>
		        </table>
		        <div class="pages">
                    <s:if test="page > 1">
                        <a href="extension/get_item_list.do?page=<s:property value="page - 1" />">上一页</a>
                    </s:if>
                    <s:if test="page > 1 && itemList.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
                    <s:if test="itemList.size() > 0">
                       <a href="extension/get_item_list.do?page=<s:property value="page + 1" />">下一页</a>
                    </s:if>
                </div>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
