<#macro getNameByObj obj>
	<#switch obj>
		<#case 1>帮助<#break>
		<#case 2>关于我们<#break>
		<#case 3>页脚版权<#break>
		<#case 4>协议<#break>
		<#case 5>帮助<#break>
		<#case 6>注册协议<#break>
		<#case 7>页脚版权<#break>
		<#default>出错了！
	</#switch>
</#macro>