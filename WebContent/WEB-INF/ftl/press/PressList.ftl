<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>合作出版社管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/press/edit/0">添加合作出版社</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;合作出版社列表
					<font class="red">${count}</font>
				</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">合作出版社ID</td>
					<td width="10%" class="left">发布位置</td>
					<td width="10%">排序</td>
					<td width="30%" class="left">合作出版社名称</td>
					<td width="20%" class="left">合作出版社图片</td>
					<td width="10%">是否显示</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (pressList?? && 0 < pressList?size)>
					<#list pressList as p>
						<tr bgColor="#<#if (0 == p_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == p_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${p.pressId}
							</td>
							<td class="left">
								<#if ("1" == p.pressLocation)>中文电子书
								<#elseif ("2" == p.pressLocation)>外文电子书
								<#elseif ("3" == p.pressLocation)>外文电子期刊
								<#else>出错了！
								</#if>
							</td>
							<td>
								${p.pressOrderby}
							</td>
							<td class="left">
								${p.pressTitle}
							</td>
							<td>
								<img src="/upload/press/${p.pressLogo}" width="100" />
							</td>
							<td>
								<#if (1 == p.pressVisible)><span class="red">不显示</span></#if>
							</td>
							<td>
								<a href="${request.contextPath}/manage/press/edit/${p.pressId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/press/del/${p.pressId}" onClick="return confirm('确定删除吗？');">删除</a>
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
