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
            <div class="sign"><p>Appbox &gt; 详情</p></div>
            <div class="post">
                <div class="detail">
                
                <table>
                   <tr><td class="title">name:</td><td><s:property value="%{appboxItem.name}" /></td></tr>
                   <tr><td class="title">source: </td><td><s:property value="%{appboxItem.source}" /></td></tr>
                   <tr><td class="title">sourceCharSet: </td><td><s:property value="%{appboxItem.charSet}" /></td></tr>
                   <tr><td class="title">titelRegex:</td><td><s:property value="%{appboxItem.titleRegex}" /></td></tr>
                   <tr><td class="title">urlRegex: </td><td><s:property value="%{appboxItem.urlRegex}" /></td></tr>
                   <tr><td class="title">picRegex: </td><td><s:property value="%{appboxItem.picRegex}" /></td></tr>
                   
                   <tr><td class="title">titel:</td><td><s:property value="%{appboxItem.title}" /></td></tr>
                   <tr><td class="title">url: </td><td><s:property value="%{appboxItem.url}" /></td></tr>
                   <tr><td class="title">imgUrl: </td><td><s:property value="%{appboxItem.imgUrl}" /></td></tr>
                  
                   <tr><td class="title">category:</td><td><s:property value="%{appboxItem.appboxCategory.name}" /></td></tr>
                   
                   <tr><td class="title">postTime: </td><td><s:property value="%{appboxItem.date}" /></td></tr>
                   <tr><td class="title">匹配状态: </td><td>
                   <s:if test="appboxItem.matchStatue == 0">全部匹配到值</s:if>
                   <s:elseif test="appboxItem.matchStatue == -1">没有匹配到值</s:elseif>
                   <s:else>部分匹配到值</s:else>
                   </td></tr>
                   
                   <tr><td class="title">matchTime: </td><td><s:property value="%{appboxItem.matchDate}" /></td></tr>
               </table>
               <div class="modify">
                   <span><a href="appbox/match.do?appboxItemId=<s:property value="appboxItem.id" />">匹 配</a></span>&nbsp;
                   <span><a href="appbox/modify_item.do?appboxItemId=<s:property value="appboxItem.id" />">修 改</a></span>
               </div>
               </div>
            </div>
        </div>
        <%@ include file="../sidebar.jsp" %> 
      
      
      <%@ include file="../footer.jsp" %> 
    </div>
    
  </body>
</html>