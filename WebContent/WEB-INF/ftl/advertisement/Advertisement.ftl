<#if (1 == advertisement.advertisementLocation)>
	<script type="text/javascript" src="./js/jquery.superslide.2.1.1.js"></script>
	<script type="text/javascript" src="./js/common.js"></script>
</#if>

<#if (1 == advertisement.advertisementLocation || 2 == advertisement.advertisementLocation)>
<div class="banner">
	<div class="outer <#if (5 == advertisement.advertisementLocation)>jourOuter</#if>">
<#elseif (3 == advertisement.advertisementLocation || 4 == advertisement.advertisementLocation || 5 == advertisement.advertisementLocation)>
	<div class="outer jourOuter">
</#if>
		<ul class="inner">
			<#if (advertisementList?? && 0 < advertisementList?size)>
				<#list advertisementList as ad>
					<li>
						<#if (1 == ad.advertisementType)>
							<#if ad.advertisementAttachment?? && ad.advertisementLink??><a href="/upload/ad/${ad.advertisementAttachment}" title="${ad.advertisementAttachment!}"><#elseif ad.advertisementLink??><a href="<#if (!ad.advertisementLink?starts_with('http'))>http://</#if>${ad.advertisementLink}" target="_blank" title="${ad.advertisementTitle!}"></#if>
								<img src="/upload/ad/${ad.advertisementPicture!}" <#if (1 == advertisement.advertisementLocation || 2 == advertisement.advertisementLocation)>width="1024" height="78"<#else>width="760" height="<#if (3 == advertisement.advertisementLocation || 4 == advertisement.advertisementLocation || 5 == advertisement.advertisementLocation)>127<#else>115</#if>"</#if> onerror="javascript:this.src='/images/nopic.jpg'" />
							<#if ad.advertisementAttachment?? || ad.advertisementLink??></a></#if>
						<#else>
							<p class="tc f14"><a style="text-decoration: none; outline: none;" href="<#if (!ad.advertisementLink?starts_with('http'))>http://</#if>${ad.advertisementLink}" target="_blank" title="${ad.advertisementTitle}">${ad.advertisementContent!}</a></p>
						</#if>
					</li>
					<#--if (1 == advertisement.advertisementLocation)>
						<#if (0 < ad_index)><#break></#if>
					</#if-->
				</#list>
			</#if>
			
			<!--
			<#if (1 == advertisement.advertisementLocation)>
				<li>
					<p class="tc f14" style="line-height: 78px; background-image: '../images/ppv.jpg'">
						<a href="/pages/collection/form/getPPV" target="_blank" title="外文文章单篇购买">外文文章单篇购买</a>
					</p>
					<a href="/pages/collection/form/getPPV" target="_blank" title="外文文章单篇购买">
						<img src="/upload/ad/ppv.jpg" width="1024" height="78" />
					</a>
				</li>
			</#if>
			-->
		</ul>
		
		<#if (2 != advertisement.advertisementLocation)>
			<a class="prev" href="javascript:void(0)"></a>
			<a class="next" href="javascript:void(0)"></a>
			<div class="btn <#if (3 == advertisement.advertisementLocation || 4 == advertisement.advertisementLocation || 5 == advertisement.advertisementLocation)>jourBtn</#if>"><ul></ul></div>
		</#if>
<#if (1 == advertisement.advertisementLocation || 2 == advertisement.advertisementLocation)>
	</div>
</div>
<#elseif (3 == advertisement.advertisementLocation || 4 == advertisement.advertisementLocation || 5 == advertisement.advertisementLocation)>
	</div>
</#if>