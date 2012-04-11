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
            <div class="sign"><p>Extension &gt; <s:text name="list.category" /></p></div>
            <div class="post">
                <div class="add">
                    <span><a href="extension/add_cat.do"> + <s:text name="add.new" /></a></span>
                </div>
                <div class="pages">
                    <s:if test="page > 1">
                        <a href="extension/list_cat.do?page=<s:property value="page - 1" />"><s:text name="next.page" /></a>
                    </s:if>
                    <s:if test="page > 1 && list.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
                    <s:if test="list.size() > 0">
                       <a href="extension/list_cat.do?page=<s:property value="page + 1" />"><s:text name="previous.page" /></a>
                    </s:if>
                </div>
                
                <table>
                <s:iterator value="list">
                    <tr>
                      <td class="name"><p>
                          <span><s:property value="name" /></span>
                          <s:property value="date" /></p>
                      </td>
                      <td class="op">
                          <p>
                               <a href="extension/modify_cat.do?categoryId=<s:property value="id" />"><s:text name="modify" /></a> | 
                               <a href="extension/delete_cat.do?categoryId=<s:property value="id" />"><s:text name="delete" /></a>
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
