<#if (3 == press.pressLocation)><p></#if>
<#if (pressList?? && 0 < pressList?size)>
	<#list pressList as p>
		<#if (1 == press.pressLocation || 2 == press.pressLocation)>
			<p>
				<a href="<#if (p.pressLink?? && 0 < p.pressLink?length)>${p.pressLink}<#else>/press/${p.pressId}</#if>" title="${p.pressTitle}" target="_blank">
					<img src="/upload/press/${p.pressLogo}" width="175" height="72" />
				</a>
			</p>
		<#else>
			<span>
				<a href="<#if (p.pressLink?? && 0 < p.pressLink?length)>${p.pressLink}<#else>/press/${p.pressId}</#if>" title="${p.pressTitle}" target="_blank">
					<img src="/upload/press/${p.pressLogo}" width="175" height="72" />
				</a>
			</span>
			<#if (3 == p_index)></p><p></#if>
		</#if>
	</#list>
</#if>
<#if (3 == press.pressLocation)></p></#if>