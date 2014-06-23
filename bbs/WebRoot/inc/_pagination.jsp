<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="#maxPage>1">
	<div class="text-center">
		<ul class="pagination">
			<li class="${p<=1?'disabled':'' }"><a href="${urlL }&p=1&ps=${ps}">&laquo;</a></li>
			<li class="${p<=1?'disabled':'' }"><a href="${urlL }&p=${p-1 }&ps=${ps}">上一页</a></li>
			<s:if test="#bz==null">
				<s:set name="bz" value="10"></s:set>
			</s:if>
			<s:if test="#maxPage<=#bz">
				<s:set name="beginPage" value="1"></s:set>
				<s:set name="endPage" value="#maxPage"></s:set>
			</s:if>
			<!-- 落在左边 -->
			<s:elseif test="#p<=#bz/2">
				<s:set name="beginPage" value="1"></s:set>
				<s:set name="endPage" value="#bz"></s:set>
			</s:elseif>
			<!-- 落在右边 -->
			<s:elseif test="#maxPage-#p<#bz/2">
				<s:set name="beginPage" value="#maxPage-#bz+1"></s:set>
				<s:set name="endPage" value="#maxPage"></s:set>
			</s:elseif>
			<!-- 落在中间 -->
			<s:else>
				<s:set name="beginPage" value="#p-#bz/2+1"></s:set>
				<s:set name="endPage" value="#p+#bz/2"></s:set>
			</s:else>

			<s:iterator var="i" begin="#beginPage" end="#endPage" step="1">
				<li class="${p==i?'active':''}"><a href="${urlL }&p=${i }&ps=${ps}">${i }</a></li>
			</s:iterator>

			<li class="${p>=maxPage?'disabled':'' }"><a href="${urlL }&p=${p+1 }&ps=${ps}">后一页</a></li>

			<li class="${p>=maxPage?'disabled':'' }"><a href="${urlL }&p=${maxPage }&ps=${ps}">&raquo;</a></li>
		</ul>
	</div>
</s:if>
