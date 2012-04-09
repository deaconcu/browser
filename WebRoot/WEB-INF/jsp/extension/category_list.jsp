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
            <div class="sign"><p>Extension > 分类列表</p></div>
            <div class="post">
                <div class="add">
                    <span><a href="extension/add_cat.do"> + 添加</a></span>
                </div>
                <div class="pages">
                    <s:if test="page > 1">
                        <a href="extension/list_cat.do?page=<s:property value="page - 1" />">上一页</a>
                    </s:if>
                    <s:if test="page > 1 && list.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
                    <s:if test="list.size() > 0">
                       <a href="extension/list_cat.do?page=<s:property value="page + 1" />">下一页</a>
                    </s:if>
                </div>
                
                <table>
                <s:iterator value="list">
                    <tr>
                      <td class="name"><p>
                          <span><s:property value="name" /></span>
                      </td>
                      <td class="op">
                          <p>
                               <a href="extension/modify_cat.do?categoryId=<s:property value="id" />">修改</a> | 
                               <a href="extension/delete_cat.do?categoryId=<s:property value="id" />">删除</a>
                          </p>
                      </td>
                    </tr>
                </s:iterator>
                </table>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>
