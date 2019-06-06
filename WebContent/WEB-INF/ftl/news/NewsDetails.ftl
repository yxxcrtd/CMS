<div class="newsLeft oh">
	<h1 class="f14">${title_news!}</h1>
	<ul class="newsdot">
	    <#if (newsList?? && 0 < newsList?size)>
			<#list newsList as n>
				<li><span class="omit w220 fl"><a <#if (n.newsId == news.newsId)>class="blank"</#if> href="/news/${n.newsId}">${n.newsTitle}</a></span><span class="fr f10">${n.newsCreateTime?string("yyyy-MM-dd")}</span></li>
			</#list>
		</#if>
	</ul>
	<div class="holder"></div>
</div>

<div class="newsRight">
	<div class="newListTit">
		<h1 class="f14 mb5">${news.newsTitle}</h1>
		<p>
			发布时间：${news.newsCreateTime?string("yyyy-MM-dd")}&nbsp;&nbsp;&nbsp;&nbsp;
			<#if news.newsAuthor??>作者：${news.newsAuthor}&nbsp;&nbsp;&nbsp;&nbsp;</#if>
			<#if news.newsUrl?? && news.newsSource??>新闻来源：<a href="${news.newsUrl}" target="_blank">${news.newsSource}</a></#if>
		</p>
	</div>
	<div class="fontFam"><p>${news.newsContent!}</p></div>
</div>

<script type="text/javascript" src="../js/jquery.jPages.js"></script>
<script type="text/javascript"><!--$(function() { $("div.holder").jPages({  }); });//--></script>