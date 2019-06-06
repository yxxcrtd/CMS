<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>菜单是否可见</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/config/menuConfig" title="二级菜单设置">二级菜单设置</a>&nbsp;&nbsp;
				</span>
			</div>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr id="thead">
					<td width="50%" class="left">菜单名称</td>
					<td width="50%">是否显示</td>
				</tr>
				<#list menuList as n>
					<tr bgColor="#<#if (0 == n_index % 2)>F9F9F9<#else>FFFFFF</#if>" onMouseOver="changeBgColor(this, '#ECECEC');" onMouseOut="changeBgColor(this, '#<#if (0 == n_index % 2)>F9F9F9<#else>FFFFFF</#if>');">
						<td class="left">${n.configCnTitle}</td>
						<td><input class="menuconfig" type="checkbox" name="target" value="${n.configId}" <#if (1 == n.configVisible)>checked</#if>></td>
					</tr>
				</#list>
			</table>
		</div>
	</body>
	
	<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
	<script language="javascript" src="${request.contextPath}/js/public.js"></script>
	<script language="javascript">
	<!--
	$(function() {
		$(".menuconfig").on("click", function() {
			var This = $(this);
			$.post("${request.contextPath}/manage/config", { "id" : This.attr("value") }, function(data) {
				if ("success" == data) {
					alert("设置成功！");
				} else {
					alert("发生了未知的错误，请重新设置！");
				}
			});
		});
	});
	//-->
	</script>
</html>
