<#if menuList?? && 0 < menuList?size>
	<#list menuList as c>
		<li><a href="${c.configUrl}">${c.configEnTitle}</a></li>
	</#list>
</#if>