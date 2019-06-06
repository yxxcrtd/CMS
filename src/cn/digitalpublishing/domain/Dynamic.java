package cn.digitalpublishing.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Dynamic Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Dynamic implements Serializable {

	/**
	 * 动态ID
	 */
	private int dynamicId;

	/**
	 * 动态对象 1：帮助；2：关于我们；3：页脚版权；4：注册条款
	 */
	private int dynamicObj;

	/**
	 * 动态中文内容
	 */
	@NotBlank
	private String dynamicChinese;

	/**
	 * 动态英文内容
	 */
	@NotBlank
	private String dynamicEnglish;

}
