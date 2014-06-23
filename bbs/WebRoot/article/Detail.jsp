    <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/_header.jsp"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="style/css/article/detail.css" rel="stylesheet">
<div class="text-center" id="title">${model.title }</div>
<div class="container text-right">
	<ul class="list-inline">
		<li>所属板块:<strong><a href="Article_list?m=${model.module.id }">${model.module.rname==null?'全部':model.module.rname }</a></strong>
		</li>
		<li>发布时间:<strong>${model.time }</strong>
		</li>
	</ul>
</div>
<div class="container">
	<table class="table" id="main">
		<tbody>
			<s:if test="commentPV.page==1">
				<tr>
					<s:set name="user" value="model.user"></s:set>
					<td id="author" class="left">
						<p>
							作者:<a href="User?id=${user.id}" target="_blank"> ${user.nickname }</a>
						</p>
						<p>金币:${user.money }</p>
						<p>等级:${user.rank }</p>
						<p>积分:${user.score }</p>
						<p>性别:${user.sex }</p>
						<p>邮箱:${user.email }</p>

					</td>
					<td class="right">${model.content }</td>
				</tr>
			</s:if>
			<s:iterator value="commentPV.rows">
				<tr>
					<td class="commentUser left">
						<p>
							评论人: <a href="User?id=${user.id}" target="_blank"> ${user.nickname }</a>
						</p>
						<p>金币:${user.money }</p>
						<p>等级:${user.rank }</p>
						<p>积分:${user.score }</p>
						<p>性别:${user.sex }</p>
						<p>邮箱:${user.email }</p>
					</td>
					<td class="right">${content }</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<div class="pull-right">
		<s:set name="p" value="commentPV.page"></s:set>
		<s:set name="ps" value="commentPV.pageSize"></s:set>
		<s:set name="total" value="commentPV.total"></s:set>
		<s:set name="maxPage" value="#total%#ps==0?#total/#ps:(#total/#ps+1)"></s:set>
		共有<strong>${total }</strong>条评论
		<s:set name="urlL" value="'Article?id='+id"></s:set>
		<%@include file="/inc/_pagination.jsp"%>

	</div>

	<div>
		<input name="id" type="hidden" id="articleId" value="${model.id }" />
		<s:if test="#session.user==null">
			要评论请先<a href="User_toLogin?href=Article?id=${model.id }">登录</a>
		</s:if>
		<s:else>
			<form id="commentForm">
				<input type='hidden' name="article.id" value="${model.id }" />
				<div class="input-group">
					<textarea class="text-primary form-control" name="content" id="content" style="width:600px;height:130px;"></textarea>
				</div>

				<div class="input-group">
					验证码: <input style="width:80px;" name="verityCode" id="verityCode" class="form-control">
        <img id="photo_yzm" class="img-thumbnail" style="width:80px;height:38px; cursor:pointer;" alt="验证码" title="点击更换图片" src="Photo_yzm"/>
    </div>
				<div class="input-group">
					<div id="submitCase">
						<input type="submit" id="submitBtn" class="btn btn-primary" value="评论" />
					</div>
				</div>
			</form>
		</s:else>
	</div>
</div>
        <br>
        <br>
        <br>
        <br>
<script src="style/js/article/detail.js"></script>
<%@include file="/inc/_toTop.jsp"%>
<%@include file="/inc/_footer.jsp"%>