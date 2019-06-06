<#import "dynamic/GetDynamicByObjChinese.ftl" as d>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><@d.getNameByObj obj /></title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag"><a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;<@d.getNameByObj obj />：</span>
				<span id="tips"><#if tips??>${tips}</#if></span>
			</div>
			
			<form action="${request.contextPath}/manage/dynamic/${obj}/save" method="post">

				<div class="label"><@d.getNameByObj obj />中文：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "dynamic.dynamicChinese" "class='input', style='width: 882px; height: 400px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label"><@d.getNameByObj obj />英文：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "dynamic.dynamicEnglish" "class='input', style='width: 882px; height: 400px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="operation">
					<input type="submit" value="保 存" class="button icon_save" />
				</div>
				
				<@s.formHiddenInput "dynamic.dynamicId" />
				<@s.formHiddenInput "dynamic.dynamicObj" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/public.js"></script>
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor1, editor2;
		KindEditor.ready(function(K) {              
			editor1 = K.create("#dynamicChinese");
			editor2 = K.create("#dynamicEnglish");
		});
		//-->
		</script>
	</body>
</html>