<#if newsList?? && 0 < newsList?size>
	<div class="news mb50">
		<h1 class="indexHtit">
			<#list newsList as c>
			<span class="fl titFb"><a class="ico3">${c.configEnTitle}</a></span>
			</#list>
			<span class="fr mt10 mr5 newListA"><a href="javascript:;"><img src="images/ico/ico12.png" /></a></span>
		</h1>
		<div id="news"></div>
	</div>
</#if>


	