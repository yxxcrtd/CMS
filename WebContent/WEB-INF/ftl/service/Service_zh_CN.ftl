<#assign filterHTML = "cn.digitalpublishing.util.FreemarkerFilterHTML"?new()>
<h1 class="indexHtit">
	<span class="titFb"><a class="ico2" href="javascript:;">服务</a></span>
</h1>

<#if (serviceList?? && 0 < serviceList?size)>
	<#list serviceList as s>
		<#if (s.serviceLink??)>
			<p>
				<span>
					<a href="/service/${s.serviceId}" class="fl">${s.serviceChineseTitle}</a>
					<a href="${s.serviceLink}" class="fr mr10">进入>></a>
				</span><br />
				<span>${filterHTML(s.serviceChineseTitle)}</span>
			</p>
		<#else>
			<p>
				<a href="/service/${s.serviceId}">${s.serviceChineseTitle}</a><br /> 
				<span><#if (91 < filterHTML(s.serviceChineseContent)?length)>${filterHTML(s.serviceChineseContent)[0..90]}...<#else>${filterHTML(s.serviceChineseContent)}</#if></span>
			</p>
		</#if>
	</#list>
</#if>
