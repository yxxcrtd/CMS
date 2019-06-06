package cn.digitalpublishing.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Advertisement Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Advertisement implements Serializable {

	/**
	 * 广告ID
	 */
	private int advertisementId;

	/**
	 * 广告状态（0：开启（默认）；1：关闭；）
	 */
	private int advertisementVisible;

	/**
	 * 广告类型（1：图片广告（默认）；2：文字广告；3，代码广告）
	 */
	private int advertisementType;

	/**
	 * 广告投放位置（1：首页顶部（可滚动）；2：首页底部（单图，不可滚动）3：二级中文（可滚动）；4：二级英文（可滚动）；5：二级期刊（可滚动）；6：Elsevier ）
	 */
	private int advertisementLocation;

	/**
	 * 广告标题
	 */
	@Length(max = 32, message = "{message.length.error}")
	private String advertisementTitle;

	/**
	 * 广告链接
	 */
	@Length(max = 128, message = "{message.length.error}")
	private String advertisementLink;

	/**
	 * 广告内容
	 */
	private String advertisementContent;

	/**
	 * 广告排序
	 */
	@NotNull
	@Range(min = 1, max = 32, message = "{message.value.error}")
	private int advertisementOrderby;

	/**
	 * 广告图片
	 */
	private String advertisementPicture;
	
	/**
	 * 广告附件
	 */
	private String advertisementAttachment;

}
