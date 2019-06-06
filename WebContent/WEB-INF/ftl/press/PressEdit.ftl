<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == press.pressId)>添加<#else>修改</#if>合作出版社</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/press" title="合作出版社列表">合作出版社列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<#if (0 == press.pressId)>添加<#else>修改</#if>合作出版社：
				</span>
			</div>
			
			<form action="${request.contextPath}/manage/press/save" method="post" enctype="multipart/form-data">
			    <div class="label">是否显示：</div>
				<div>
					<@s.formRadioButtons "press.pressVisible" visibleMap />
				</div>
				
			    <div class="label">合作出版社的发布位置：</div>
				<div>
					<@s.formSingleSelect "press.pressLocation" locationMap "class='selectStyle'" />
				</div>
				
				<div class="label">合作出版社的排序：<span class="star">*</span></div>
				<div>
					<@s.formInput "press.pressOrderby" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">合作出版社的名称：<span class="star">*</span></div>
				<div>
					<@s.formInput "press.pressTitle" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">合作出版社的链接：<span class="star">（优先级别高于内容）</span></div>
				<div>
					<@s.formInput "press.pressLink" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">合作出版社的图片：<span class="star">*</span>（图片的宽度是：175像素）</div>
				<div>
					<input type="file" class="input" style="width: 500px;" name="file" id="file" />
					<@s.formHiddenInput "press.pressLogo" /><@s.showErrors classOrStyle="red" />
					<#if (0 lt press.pressId)>
						<br />
						<img src="/upload/press/${press.pressLogo}" />
					</#if>
				</div>
				
				<div class="label">合作出版社内容：<span class="star">*</span>
					（导入内容XML：<input type="file" class="input" style="width: 500px; padding: 1px;" id="xmlFile" multiple="multiple" />）
				</div>
				<div>
					<@s.formTextarea "press.pressContent" "class='input', style='width: 882px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">发布到：</div>
				<div>
					<input type="checkbox" name="target" value="1" checked />前台门户
					<input type="checkbox" name="target" value="2" disabled />海外版
					<input type="checkbox" name="target" value="3" disabled />移动版
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == press.pressId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "press.pressId" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create("#pressContent", { filterMode : false });
		});
		
		$(function() {
			$("#file").on("change", function() {
				var file = this.files[0];
				// console.info("======file======" + file.name + " - " + file.size + " - " + file.type);
				$("#pressLogo").attr("value", file.name);
			});
			
		
			$("#xmlFile").on("change", function() {
				var xhr = new XMLHttpRequest();
				var files = document.getElementById('xmlFile').files;
				var formData = new FormData();
				formData.append('xmlFile', files[0]);
				xhr.open("post", "${request.contextPath}/manage/press/xmlparser");
				xhr.send(formData);
				xhr.onload = function(e) {
					if (200 == this.status) {
						editor.html(this.responseText);
					}
				}
			});
		});
		//-->
		</script>
	</body>
</html>
