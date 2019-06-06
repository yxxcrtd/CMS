package cn.digitalpublishing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.digitalpublishing.domain.Config;

/**
 * Config Controller
 */
@Controller
@RequestMapping("manage")
public class ConfigController extends BaseController {

	/**
	 * 二级菜单配置列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "menuConfig", method = RequestMethod.GET)
	public ModelAndView menuConfig() {
		return new ModelAndView("config/MenuConfig", "menuList", configService.findListByConfigType(STRING_MENU));
	}

	/**
	 * 新闻配置
	 * 
	 * @return
	 */
	@RequestMapping(value = "newsConfig", method = RequestMethod.GET)
	public ModelAndView newsConfig() {
		return new ModelAndView("config/NewsConfig", "newsList", configService.findListByConfigType(STRING_NEWS));
	}

	/**
	 * 配置二级菜单与新闻是否显示
	 * 
	 * @param configId
	 * @return
	 */
	@RequestMapping(value = "config", method = RequestMethod.POST)
	public @ResponseBody String audit(@RequestParam(value = "id", required = true, defaultValue = "1") int configId, HttpServletRequest request) {
		Config c = configService.findById(configId);
		int configVisible = c.getConfigVisible();
		try {
			if (INT_ONE == configVisible) {
				c.setConfigVisible(INT_ZERO);
			} else {
				c.setConfigVisible(INT_ONE);
			}
			configService.update(c);

			if (STRING_MENU.equals(c.getConfigType())) {
				List<Config> menuList = configService.findListByConfigType(INT_ONE, STRING_MENU);
				// Map<String, Object> map = new HashMap<String, Object>();
				// map.put("menuList", menuList);
				// FileUtil.generateHTML("WEB-INF/ftl/config", "MenuList_zh_CN.ftl", "menuList_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_CONFIG);
				// FileUtil.generateHTML("WEB-INF/ftl/config", "MenuList_en_US.ftl", "menuList_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_CONFIG);
				configService.del("config_menu_zh_CN");
				configService.del("config_menu_en_US");
				if (null != menuList) {
					for (int i = 0; i < menuList.size(); i++) {
						configService.zadd("config_menu_zh_CN", (i + 1), "<li><a href=\"" + menuList.get(i).getConfigUrl() + "\">" + menuList.get(i).getConfigCnTitle() + "</a></li>");
						configService.zadd("config_menu_en_US", (i + 1), "<li><a href=\"" + menuList.get(i).getConfigUrl() + "\">" + menuList.get(i).getConfigEnTitle() + "</a></li>");
					}
				}
			}

			if (STRING_NEWS.equals(c.getConfigType())) {
				// List<Config> newsList = configService.findListByConfigType(INT_ONE, STRING_NEWS);
				// Map<String, Object> newsMap = new HashMap<String, Object>();
				// newsMap.put("newsList", newsList);
				// FileUtil.generateHTML("WEB-INF/ftl/config", "NewsList_zh_CN.ftl", "newsList_zh_CN.html", newsMap, request.getSession().getServletContext(), UPLOAD_PATH_CONFIG);
				// FileUtil.generateHTML("WEB-INF/ftl/config", "NewsList_en_US.ftl", "newsList_en_US.html", newsMap, request.getSession().getServletContext(), UPLOAD_PATH_CONFIG);

				configService.del("config_news");
				if (INT_ONE == configVisible) {
					configService.lpush("config_news", "0"); // 不显示
				} else {
					configService.lpush("config_news", "1"); // 显示
				}
			}
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

}
