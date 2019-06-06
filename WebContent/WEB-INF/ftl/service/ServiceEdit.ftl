<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == service.serviceId)>发布<#else>修改</#if>服务</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/service" title="服务列表">服务列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<#if (0 == service.serviceId)>发布<#else>修改</#if>服务：
				</span>
			</div>
			
			<form action="${request.contextPath}/manage/service/save" method="post">
			    <div class="label">服务排序：<span class="star">*</span></div>
				<div>
					<@s.formInput "service.serviceOrderby" "class='input'"/><@s.showErrors classOrStyle="red" />
				</div>
				
			    <div class="label">服务中文标题：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "service.serviceChineseTitle" "class='input872', style='width: 882px; height: 30px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">服务中文内容：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "service.serviceChineseContent" "class='input', style='width: 882px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
			    <div class="label">服务英文标题：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "service.serviceEnglishTitle" "class='input872', style='width: 882px; height: 30px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">服务英文内容：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "service.serviceEnglishContent" "class='input', style='width: 882px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">服务链接：（外文文章单篇购买列表）</div>
				<div>
					<@s.formInput "service.serviceLink" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">发布到：</div>
				<div>
					<input type="checkbox" name="target" value="1" checked />前台门户
					<input type="checkbox" name="target" value="2" disabled />海外版
					<input type="checkbox" name="target" value="3" disabled />移动版
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == service.serviceId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "service.serviceId" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor1, editor2, editor3, editor4;
		KindEditor.ready(function(K) {              
			editor1 = K.create("#serviceChineseTitle", { pasteType : 1, newlineTag : "br" });
			editor2 = K.create("#serviceEnglishTitle", { pasteType : 1, newlineTag : "br" });
			editor3 = K.create("#serviceChineseContent");
			editor4 = K.create("#serviceEnglishContent");
		});
		//-->
		</script>
	</body>
</html>
