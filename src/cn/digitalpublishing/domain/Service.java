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
 * Service Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Service implements Serializable {

	/**
	 * 服务ID
	 */
	private int serviceId;

	/**
	 * 服务中文标题
	 */
	@NotBlank
	@Length(min = 1, max = 1024, message = "{message.length.error}")
	private String serviceChineseTitle;

	/**
	 * 服务中文内容
	 */
	@NotBlank
	private String serviceChineseContent;

	/**
	 * 服务英文标题
	 */
	@NotBlank
	@Length(min = 1, max = 1024, message = "{message.length.error}")
	private String serviceEnglishTitle;

	/**
	 * 服务英文内容
	 */
	@NotBlank
	private String serviceEnglishContent;

	/**
	 * 服务链接
	 */
	@Length(max = 128, message = "长度不能超过128")
	private String serviceLink;

	/**
	 * 服务排序
	 */
	@NotNull
	@Range(min = 1, max = 10, message = "{message.value.error}")
	private int serviceOrderby;

}
