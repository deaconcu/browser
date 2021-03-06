<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="../top.jsp"%>

<!DOCTYPE HTML>
<html>
<%@ include file="../head.jsp"%>
<body>
	<div id="page">
		<%@ include file="../header.jsp"%>
		<div id="wrapper"></div>
		<div id="content">

			<div class="post">
				<div class="sign">
					<p>Appbox &gt; 列表</p>
				</div>
				<div class="add">
					<span><a href="appbox/add_item.do"> + 添加</a>
					</span>
				</div>
				<div class="pages">
					<s:if test="page > 1">
						<a
							href="appbox/get_item_list.do?page=<s:property value="page - 1" />">上一页</a>
					</s:if>
					<s:if test="page > 1 && listItem.size() > 0">
	                    &nbsp; | &nbsp;
	                </s:if>
					<s:if test="listItem.size() > 0">
						<a
							href="appbox/get_item_list.do?page=<s:property value="page + 1" />">下一页</a>
					</s:if>
				</div>

				<table>
					<s:iterator value="listItem">
						<tr>
							<td class="name"><p>
									<span><a
										href="appbox/get_item.do?appboxItemId=<s:property value="id" />"><s:property
												value="name" />
									</a> <s:if test="isDefault == 0"></s:if> <s:else>TOP</s:else> </span> 类别：
									<s:property value="appboxCategory.name" />
									| postTime:
									<s:property value="date" />
									|
									<s:if test="matchStatue == 0"> 全部匹配到值</s:if>
									<s:elseif test="matchStatue == -1">没有匹配到值</s:elseif>
									<s:else> 部分匹配到值</s:else>

								</p></td>
							<td class="op">
								<p>
									<a
										href="appbox/get_item.do?appboxItemId=<s:property value="id" />">详细</a>
									| <a
										href="appbox/modify_item.do?appboxItemId=<s:property value="id" />">修改</a>
									| <a
										href="appbox/delete_item.do?appboxItemId=<s:property value="id" />">删除</a><br />
									
									<s:if test="isDefault == 0">
										<a
											href="appbox/default_item.do?isDefault=1&appboxItemId=<s:property value="id" /> ">设置首页显示</a>
									</s:if>
									<s:else>
										<a
											href="appbox/default_item.do?isDefault=0&appboxItemId=<s:property value="id" /> ">取消首页显示</a>
									</s:else>
								</p></td>
						</tr>
					</s:iterator>
				</table>

				<div class="pages">
					<s:if test="page > 1">
						<a
							href="appbox/get_item_list.do?page=<s:property value="page - 1" />">上一页</a>
					</s:if>
					<s:if test="page > 1 && listItem.size() > 0">
                        &nbsp; | &nbsp;
                    </s:if>
					<s:if test="listItem.size() > 0">
						<a
							href="appbox/get_item_list.do?page=<s:property value="page + 1" />">下一页</a>
					</s:if>
				</div>

			</div>
		</div>
		<%@ include file="../sidebar.jsp"%>
		<%@ include file="../footer.jsp"%>
	</div>

</body>
</html>
