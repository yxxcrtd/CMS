package cn.digitalpublishing.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Dynamic;
import freemarker.template.TemplateException;

/**
 * Dynamic Controller
 */
@Controller
@RequestMapping("manage/dynamic/{obj}")
public class DynamicController extends BaseController {

	/**
	 * Edit
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable(value = "obj") int obj) {
		dynamic = dynamicService.findByDynamicObj(obj);
		if (null == dynamic) {
			dynamic = new Dynamic();
			dynamic.setDynamicObj(obj);
		}
		return new ModelAndView("dynamic/DynamicEdit", "dynamic", dynamic);
	}

	/**
	 * Save
	 * 
	 * @param dynamic
	 * @param result
	 * @param obj
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("dynamic") @Valid Dynamic dynamic, BindingResult result, @PathVariable(value = "obj") int obj, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("dynamic/DynamicEdit", "Dynamic", dynamic);
		}

		if (0 == dynamic.getDynamicId()) {
			dynamicService.save(dynamic);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			dynamicService.update(dynamic);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		// 往 Redis 里写数据
		writeRedis(obj, dynamic);

		return new ModelAndView("redirect:/manage/dynamic/" + obj);
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void writeRedis(int obj, Dynamic d) {
		switch (obj) {
		case 1: // 帮助
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_zh_CN.ftl", "help_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_en_US.ftl", "help_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			dynamicService.lpush("help_zh_CN", "<div class=\"help\"><h1 class=\"helpH1 f14\">帮助</h1><p></p>" + d.getDynamicChinese() + "</div>");
			dynamicService.lpush("help_en_US", "<div class=\"help\"><h1 class=\"helpH1 f14\">Help</h1><p></p>" + d.getDynamicEnglish() + "</div>");
			break;
		case 2: // 关于我们
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_zh_CN.ftl", "aboutus_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_en_US.ftl", "aboutus_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			dynamicService.lpush("aboutus_zh_CN", "<div class=\"help\"><h1 class=\"helpH1 f14\">关于我们</h1><p></p>" + d.getDynamicChinese() + "</div>");
			dynamicService.lpush("aboutus_en_US", "<div class=\"help\"><h1 class=\"helpH1 f14\">About Us</h1><p></p>" + d.getDynamicEnglish() + "</div>");
			break;
		case 3: // 页脚版权
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_zh_CN.ftl", "footer_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_en_US.ftl", "footer_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			dynamicService.lpush("footer_zh_CN", d.getDynamicChinese());
			dynamicService.lpush("footer_en_US", d.getDynamicEnglish());
			break;
		case 4: // 注册协议
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_zh_CN.ftl", "agreement_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			// FileUtil.generateHTML("WEB-INF/ftl/dynamic", "Dynamic_en_US.ftl", "agreement_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_ALL);
			dynamicService.lpush("agreement_zh_CN", "<div class=\"help\"><h1 class=\"helpH1 f14\">注册协议</h1><p></p>" + d.getDynamicChinese() + "</div>");
			dynamicService.lpush("agreement_en_US", "<div class=\"help\"><h1 class=\"helpH1 f14\">Agreement</h1><p></p>" + d.getDynamicEnglish() + "</div>");
			break;
		case 5: // 移动版帮助
			dynamicService.lpush("help_m_zh_CN", "<div data-role=\"header\" class=\"classifyTit\"><a class=\"back\" href=\"javascript:history.back(-1);\"><img src=\"/mobile/images/left.png\"></a><h1 class=\"ui-title-h1\">帮助</h1></div>" + d.getDynamicChinese() + "</div>");
			break;
		case 6: // 移动版注册协议
			dynamicService.lpush("agreement_m_zh_CN", "<div data-role=\"header\" class=\"classifyTit\"><a class=\"back\" href=\"javascript:history.back(-1);\"><img src=\"/mobile/images/left.png\"></a><h1 class=\"ui-title-h1\">注册协议</h1></div>" + d.getDynamicChinese() + "</div>");
			break;
		case 7: // 移动版页脚版权
			dynamicService.lpush("footer_m_zh_CN", d.getDynamicChinese());
			break;
		default:
			break;
		}
	}
}
