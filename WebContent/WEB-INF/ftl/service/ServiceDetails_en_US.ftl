<div class="personLeft">
	<div class="perLeftPart">
	    <#if (serviceList?? && 0 < serviceList?size)>
			<#list serviceList as s>
				<p><a href="/service/${s.serviceId}" <#if (s.serviceId == service.serviceId)>class="perLiA"</#if>>${s.serviceEnglishTitle}</a></p>
			</#list>
		</#if>
	</div>
</div>
<div class="perRight">
	<h1>${service.serviceEnglishTitle}</h1>
	${service.serviceEnglishContent}
</div>