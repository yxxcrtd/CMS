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
		<h1 class="f14 mb5">${news.newsTitle!}</h1>
		<p>发布日期：${news.newsCreateTime?string("yyyy-MM-dd")}     作者：${news.newsAuthor!}     新闻来源：${news.newsSource!}</p>
	</div>
	<div class="fontFam"><p>${news.newsContent!}</p></div>
</div>
