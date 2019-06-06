<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == advertisement.advertisementId)>添加<#else>修改</#if>广告</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/advertisement" title="广告列表">广告列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<#if (0 == advertisement.advertisementId)>添加<#else>修改</#if>广告：
				</span>
			</div>
			
			<form action="${request.contextPath}/manage/advertisement/save" method="post" enctype="multipart/form-data">
			    <div class="label">广告状态：</div>
				<div>
					<@s.formRadioButtons "advertisement.advertisementVisible" visibleMap />
				</div>
				
			    <div class="label">广告的类型：</div>
				<div>
					<@s.formSingleSelect "advertisement.advertisementType" typeMap "class='selectStyle'" />
				</div>
				
			    <div class="label">广告的投放位置：</div>
				<div>
					<@s.formSingleSelect "advertisement.advertisementLocation" locationMap "class='selectStyle'" />
				</div>
				
				<div class="label">广告的标题：</div>
				<div>
					<@s.formInput "advertisement.advertisementTitle" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">广告的链接：</div>
				<div>
					<@s.formInput "advertisement.advertisementLink" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="adContent">
					<div class="label">广告的内容：</div>
					<div>
						<@s.formTextarea "advertisement.advertisementContent" "class='input', style='width: 882px; height: 200px;'" /><@s.showErrors classOrStyle="red" />
					</div>
				</div>
				
				<div class="label">广告的排序：<span class="star">*</span></div>
				<div>
					<@s.formInput "advertisement.advertisementOrderby" "class='input'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div id="adPicture">
					<div class="label">广告的图片：（首页图片的尺寸：宽1024像素 * 高80像素）（二级页面图片的尺寸：宽760像素 * 高127像素）</div>
					<div>
						<input type="file" class="input" style="width: 300px;" name="file" />
						<@s.formHiddenInput "advertisement.advertisementPicture" /><@s.showErrors classOrStyle="red" />
						<#if (0 lt advertisement.advertisementId && advertisement.advertisementPicture??)>
							<br />
							<img src="/upload/ad/${advertisement.advertisementPicture}" />
						</#if>
					</div>
				</div>
				
				<div id="adPicture">
					<div class="label">广告的附件：<span class="star">（优先级别高于广告链接）</span></div>
					<div>
						<input type="file" class="input" style="width: 300px;" name="attachment" />
						<@s.formHiddenInput "advertisement.advertisementAttachment" /><@s.showErrors classOrStyle="red" />
						<#if (0 lt advertisement.advertisementId && advertisement.advertisementAttachment??)>
							<br />${advertisement.advertisementAttachment}
						</#if>
					</div>
				</div>
				
				<div class="label">发布到：</div>
				<div>
					<input type="checkbox" name="target" value="1" checked />前台门户
					<input type="checkbox" name="target" value="2" disabled />海外版
					<input type="checkbox" name="target" value="3" disabled />移动版
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == advertisement.advertisementId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "advertisement.advertisementId" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/jquery.js"></script>
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor1;
		KindEditor.ready(function(K) {              
			editor = K.create("#advertisementContent", { pasteType : 1 });
		});
		$(function() {
			$("#adContent").hide();
			
			if ("2" == ${advertisement.advertisementType} || "3" == ${advertisement.advertisementType}) {
				$("#adContent").fadeIn();
				$("#adPicture").fadeOut();
			}
		});
		
		$("#advertisementType").bind("change", function() {
			//var val = $(this).children("option:selected").val();
			var val = $(this).val();
			if ("1" == val) {
				$("#adContent").fadeOut();
				$("#adPicture").fadeIn();
			}
			if ("2" == val || "3" == val) {
				$("#adContent").fadeIn();
				$("#adPicture").fadeOut();
			}
		});
		//-->
		</script>
	</body>
</html>
