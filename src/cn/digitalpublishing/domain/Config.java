package cn.digitalpublishing.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Menu Object
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class Config implements Serializable {

	/**
	 * 菜单ID
	 */
	private int configId;

	/**
	 * 菜单中文标题
	 */
	private String configCnTitle;

	/**
	 * 菜单外文标题
	 */
	private String configEnTitle;

	/**
	 * 菜单Url
	 */
	private String configUrl;

	/**
	 * 是否显示： 0-不显示 1-显示
	 */
	private int configVisible;

	/**
	 * 配置类型： Menu-二级菜单 News-新闻
	 */
	private String configType;

}
