<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>菜单管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/news/edit/0">发布新闻</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">新闻信息列表</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">新闻ID</td>
					<td width="35%" class="left">新闻标题</td>
					<td width="20%" class="left">新闻来源</td>
					<td width="10%" class="left">新闻作者</td>
					<td width="15%">新闻发布时间</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (newsList?? && 0 < newsList?size)>
					<#list newsList as n>
						<tr bgColor="#<#if (0 == n_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == n_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${n.newsId}
							</td>
							<td class="left">
								<a href="<#if (!n.newsUrl?starts_with('http'))>http://</#if>${n.newsUrl}" target="_blank">${n.newsTitle}</a>
							</td>
							<td class="left">
								${n.newsSource}
							</td>
							<td class="left">
								${n.newsAuthor}
							</td>
							<td>
								${n.newsCreateTime}
							</td>
							<td>
								<a href="${request.contextPath}/manage/news/edit/${n.newsId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/news/del/${n.newsId}" onClick="return confirm('确定删除吗？');">删除</a>
							</td>
						</tr>
					</#list>
				<#else>
					<tr bgColor="F9F9F9">
						<td colspan="6">没有数据！</td>
					</tr>
				</#if>
	  		</table>
	  		<div id="pageNav">
	  			<#include "Pager.ftl" />
	  		</div>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	</body>
</html>
