<#if (3 == obj)>
	${dynamic.dynamicEnglish}
<#else>
	<#include "GetDynamicByObjEnglish.ftl">
	<div class="help">
		<h1 class="helpH1 f14"><@getNameByObj obj /></h1><p></p>
		${dynamic.dynamicEnglish}
	</div>
</#if>