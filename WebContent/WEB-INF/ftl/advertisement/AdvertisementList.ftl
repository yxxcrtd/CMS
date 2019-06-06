<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>广告管理</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="new">
				<a class="button icon_add" href="${request.contextPath}/manage/advertisement/edit/0">添加广告</a>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;广告列表
					<font class="red">${count}</font>
				</span>
			</div>
			
	  		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  			<tr id="thead">
					<td width="10%">广告类型</td>
					<td width="10%">发布位置</td>
					<td width="5%">排序</td>
					<td width="15%" class="left">广告标题</td>
					<td width="45%" class="left">广告图片</td>
					<td width="5%">状态</td>
					<td width="10%">操作</td>
  				</tr>
				<#if (adList?? && 0 < adList?size)>
					<#list adList as a>
						<tr bgColor="#<#if (0 == a_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == a_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
							<td>
								<#if ("1" == a.advertisementType)>图片广告
								<#elseif ("2" == a.advertisementType)>文字广告
								<#elseif ("3" == a.advertisementType)>代码广告
								<#else>出错了！
								</#if>
							</td>
							<td>
								<#if ("1" == a.advertisementLocation)>首页顶部
								<#elseif ("2" == a.advertisementLocation)>首页底部
								<#elseif ("3" == a.advertisementLocation)>二级中文
								<#elseif ("4" == a.advertisementLocation)>二级英文
								<#elseif ("5" == a.advertisementLocation)>二级期刊
								<#elseif ("6" == a.advertisementLocation)>Elsevier
								<#else>出错了！
								</#if>
							</td>
							<td>
								${a.advertisementOrderby}
							</td>
							<td class="left">
								<#if (a.advertisementTitle??)>
									<a href="<#if (!a.advertisementLink?starts_with('http'))>http://</#if>${a.advertisementLink}" target="_blank">${a.advertisementTitle}</a>
								</#if>
								<#if (a.advertisementAttachment??)>
									<a href="/upload/ad/${a.advertisementAttachment}">
									<img src="${request.contextPath}/images/pin.gif" />
									</a>
								</#if>
							</td>
							<td class="left">
								<#if a.advertisementPicture??>
									<img src="/upload/ad/${a.advertisementPicture}" width="80%" />
								</#if>
							</td>
							<td>
								<#if (0 == a.advertisementVisible)>开启<#else><span class="red">关闭</span></#if>
							</td>
							<td>
								<a href="${request.contextPath}/manage/advertisement/edit/${a.advertisementId}">编辑</a>&nbsp;&nbsp;
								<a href="${request.contextPath}/manage/advertisement/del/${a.advertisementId}" onClick="return confirm('确定删除吗？');">删除</a>
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
