<#if (3 == obj)>
	${dynamic.dynamicChinese}
<#else>
	<#include "GetDynamicByObjChinese.ftl">
	<div class="help">
		<h1 class="helpH1 f14"><@getNameByObj obj /></h1><p></p>
		${dynamic.dynamicChinese}
	</div>
</#if>