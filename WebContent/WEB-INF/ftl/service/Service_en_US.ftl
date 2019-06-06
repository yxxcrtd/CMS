<#assign filterHTML = "cn.digitalpublishing.util.FreemarkerFilterHTML"?new()>
<h1 class="indexHtit">
	<span class="titFb"><a class="ico2" href="javascript:;">Service</a></span>
</h1>

<#if (serviceList?? && 0 < serviceList?size)>
	<#list serviceList as s>
		<#if (s.serviceLink??)>
			<p>
				<span>
					<a href="/service/${s.serviceId}" class="fl">${s.serviceEnglishTitle}</a>
					<a href="${s.serviceLink}" class="fr mr10">GO>></a>
				</span><br />
				<span>${filterHTML(s.serviceEnglishTitle)}</span>
			</p>
		<#else>
			<p class="wrap">
				<a href="/service/${s.serviceId}">${s.serviceEnglishTitle}</a><br /> 
				<span><#if (192 < filterHTML(s.serviceEnglishContent)?length)>${filterHTML(s.serviceEnglishContent)[0..191]}...<#else>${filterHTML(s.serviceEnglishContent)}</#if></span>
			</p>
		</#if>
	</#list>
</#if>
