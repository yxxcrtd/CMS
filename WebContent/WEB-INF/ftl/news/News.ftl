<#assign filterHTML = "cn.digitalpublishing.util.FreemarkerFilterHTML"?new()>
<ul class="dot">
    <#if (newsList?? && 0 < newsList?size)>
		<#list newsList as n>
			<li>
				<span class="fl omit w210"><a href="/news/${n.newsId}">${n.newsTitle}</a></span>
				<span class="fr f10">${n.newsCreateTime?string("yyyy-MM-dd")}</span>
			</li>
			<#if (n_index == 9)><#break></#if>
		</#list>
	</#if>
</ul>

<script type="text/javascript">
<!--
$(function() {
	$newList = $(".dot").children("li:nth-child(n+5)");
	$newList.hide();
	var $togListA = $("span.newListA > a");
	
	$togListA.click(function() {
		if ($newList.is(":visible")) {
			$newList.fadeOut();                  
			$(this).html("<img src='images/ico/ico12.png' />").fadeIn();                 	
		} else {
			$newList.fadeIn();                   		
			$(this).html("<img src='images/ico/ico19.png' />").fadeIn();                
		}
		return false;
	});
});
//-->
</script>