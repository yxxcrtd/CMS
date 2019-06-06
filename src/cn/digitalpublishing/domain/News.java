package cn.digitalpublishing.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * News Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class News implements Serializable {

	/**
	 * 新闻ID
	 */
	private int newsId;

	/**
	 * 新闻标题
	 */
	@NotBlank
	@Length(min = 1, max = 1024, message = "{message.length.error}")
	private String newsTitle;

	/**
	 * 新闻来源
	 */
	@Length(max = 128, message = "{message.length.error}")
	private String newsSource;

	/**
	 * 新闻外部连接
	 */
	@Length(max = 128, message = "{message.length.error}")
	private String newsUrl;

	/**
	 * 新闻内容
	 */
	@NotBlank
	private String newsContent;

	/**
	 * 新闻作者
	 */
	private String newsAuthor;

	/**
	 * 新闻发布时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date newsCreateTime;

	/**
	 * 新闻排序
	 */
	@NotNull
	@Range(min = 1, max = 32, message = "{message.value.error}")
	private int newsOrderby;

}
