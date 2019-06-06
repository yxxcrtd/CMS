<ul class="inner">
	<#if (advertisementList?? && 0 < advertisementList?size)>
		<#list advertisementList as ad>
			<li>
				<a href="<#if (!ad.advertisementLink?starts_with('http'))>http://</#if>${ad.advertisementLink}" target="_blank">
					<img src="/upload/ad/${ad.advertisementPicture!}" alt="" />
				</a>
			</li>
		</#list>
	</#if>
</ul>
<a class="prev" href="javascript:;"></a>
<a class="next" href="javascript:;"></a>
<div class="btn"><ul></ul></div>

<script type="text/javascript">
<!--
        $(".outer").hover(function(){
            $(this).find(".prev,.next").fadeTo("show",0.1);
        },function(){
            $(this).find(".prev,.next").hide();
        })
        $(".prev,.next").hover(function(){
            $(this).fadeTo("show",0.7);
        },function(){
            $(this).fadeTo("show",0.1);
        })
        $(".outer").slide({ titCell:".btn ul" , mainCell:".inner" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
-->
</script>