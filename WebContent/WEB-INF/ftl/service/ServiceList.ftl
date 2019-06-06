<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>服务管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/service/edit/0">发布服务</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="返回首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;服务信息列表
					<font class="red">${serviceList?size}</font>
				</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">服务排序</td>
					<td width="30%" class="left">服务中文标题</td>
					<td width="30%" class="left">服务英文标题</td>
					<td width="20%" class="left">服务链接</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (serviceList?? && 0 < serviceList?size)>
					<#list serviceList as s>
						<tr bgColor="#<#if (0 == s_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == s_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								${s.serviceOrderby}
							</td>
							<td class="left">
								${s.serviceChineseTitle}
							</td>
							<td class="left">
								${s.serviceEnglishTitle}
							</td>
							<td class="left">
								${s.serviceLink}
							</td>
							<td>
								<a href="${request.contextPath}/manage/service/edit/${s.serviceId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/service/del/${s.serviceId}" onClick="return confirm('确定删除吗？');">删除</a>
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
