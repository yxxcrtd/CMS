package cn.digitalpublishing.domain;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Press Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Press implements Serializable {

	/**
	 * 合作出版社ID
	 */
	private int pressId;

	/**
	 * 合作出版社发布位置 1：中文；2：英文；3：期刊；
	 */
	private int pressLocation;

	/**
	 * 合作出版社排序
	 */
	@NotNull
	@Range(min = 1, max = 64, message = "{message.value.error}")
	private int pressOrderby;

	/**
	 * 合作出版社名称
	 */
	@NotBlank
	@Length(min = 1, max = 64, message = "{message.length.error}")
	private String pressTitle;

	/**
	 * 合作出版社链接
	 */
	@Length(max = 256, message = "长度不能超过256")
	private String pressLink;

	/**
	 * 合作出版社内容
	 */
	@NotBlank
	private String pressContent;

	/**
	 * 合作出版社图片
	 */
	@NotBlank
	private String pressLogo;

	/**
	 * 图片是否显示（0：显示（默认）；1：不显示；）
	 */
	private int pressVisible;

}
