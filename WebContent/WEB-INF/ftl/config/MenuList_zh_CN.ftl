<#if menuList?? && 0 < menuList?size>
	<#list menuList as c>
		<li><a href="${c.configUrl}">${c.configCnTitle}</a></li>
	</#list>
</#if>