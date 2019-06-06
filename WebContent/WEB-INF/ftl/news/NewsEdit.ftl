<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><#if (0 == news.newsId)>发布<#else>修改</#if>新闻</title>
		<link type="text/css" rel="styleSheet" href="${request.contextPath}/css/style.css" />
	</head>

	<body>
		<div id="content">
			<div id="header">
				<span class="icon_flag">
					<a href="${request.contextPath}" title="首页">首页</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<a href="${request.contextPath}/manage/news" title="新闻列表">新闻列表</a>&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;
					<#if (0 == news.newsId)>发布<#else>修改</#if>新闻：
				</span>
			</div>
			
			<form action="${request.contextPath}/manage/news/save" method="post">
			    <div class="label">新闻排序权重：<span class="star">*</span></div>
				<div>
					<@s.formInput "news.newsOrderby" "class='input'"/><@s.showErrors classOrStyle="red" />
				</div>
				
			    <div class="label">新闻标题：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "news.newsTitle" "class='input', style='width: 882px; height: 30px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">新闻来源：</div>
				<div>
					<@s.formInput "news.newsSource" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">新闻外部链接：</div>
				<div>
					<@s.formInput "news.newsUrl" "class='input500'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">新闻内容：<span class="star">*</span></div>
				<div>
					<@s.formTextarea "news.newsContent" "class='input', style='width: 882px; height: 500px;'" /><@s.showErrors classOrStyle="red" />
				</div>
				
				<div class="label">新闻作者：</div>
				<div>
					<@s.formInput "news.newsAuthor" "class='input'" />
				</div>
				
				<div class="label">发布到：</div>
				<div>
					<input type="checkbox" name="target" value="1" checked />前台门户
					<input type="checkbox" name="target" value="2" disabled />海外版
					<input type="checkbox" name="target" value="3" disabled />移动版
				</div>
				
				<div id="operation">
					<input type="submit" value="<#if (0 == news.newsId)>保 存<#else>修 改</#if>" class="button icon_save" />&nbsp;&nbsp;
					<input type="button" value="返 回" class="button icon_return" onClick="javascript:history.back();" />
				</div>
				
				<@s.formHiddenInput "news.newsId" />
				<@s.formHiddenInput "news.newsCreateTime" />
			</form>
		</div>
		
		<script language="javascript" src="${request.contextPath}/js/kindeditor.js"></script>
		<script language="javascript">
		<!--
		var editor1, editor2;
		KindEditor.ready(function(K) {
			editor1 = K.create("#newsContent");
			editor2 = K.create("#newsTitle", { pasteType : 1, newlineTag : "br" });
		});
		//-->
		</script>
	</body>
</html>
