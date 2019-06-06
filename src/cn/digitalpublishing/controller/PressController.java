package cn.digitalpublishing.controller;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Press;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;

/**
 * Press Controller
 */
@Controller
@RequestMapping("manage/press")
public class PressController extends BaseController {

	/**
	 * List
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = pressService.findAllCount(press);
		pager.setTotalCount(count);
		mav.addObject("pressList", pressService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.setViewName("press/PressList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param pressId
	 * @param request
	 * @return
	 */
	@RequestMapping("edit/{pressId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "pressId") int pressId, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (0 == pressId) {
			press = new Press();
			press.setPressId(pressId);
		} else {
			press = pressService.findById(pressId);
		}
		mav.addObject("visibleMap", getVisibleMap());
		mav.addObject("locationMap", getLocationMap());
		mav.addObject("press", press);
		mav.addObject("request", request);
		mav.setViewName("press/PressEdit");
		return mav;
	}
	
	private Map<String, String> getLocationMap() {
		Map<String, String> locationMap = new LinkedHashMap<String, String>();
		locationMap.put("2", "外文电子书");
		locationMap.put("1", "中文电子书");
		locationMap.put("3", "外文电子期刊");
		return locationMap;
	}

	private Map<String, String> getVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "显示");
		visibleMap.put("1", "不显示");
		return visibleMap;
	}

	/**
	 * Save
	 * 
	 * @param press
	 * @param result
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView Save(@ModelAttribute("press") @Valid Press press, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			Map<String, String> locationMap = new LinkedHashMap<String, String>();
			locationMap.put("2", "外文电子书");
			locationMap.put("1", "中文电子书");
			locationMap.put("3", "外文电子期刊");
			mav.addObject("locationMap", locationMap);
			mav.addObject("press", press);
			mav.setViewName("press/PressEdit");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				press.setPressLogo(FileUtil.uploadFile(file, UPLOAD_PATH_PRESS, false));
			}
		}

		if (0 == press.getPressId()) {
			pressService.save(press);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			pressService.update(press);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pressList", pressService.findListByLocation(press.getPressLocation()));
		map.put("press", press);
		map.put("request", request);
		try {
			generateHTML(map, request);
			FileUtil.generateHTML("WEB-INF/ftl/press", "PressDetail.ftl", press.getPressId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH_PRESS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/manage/press");
	}

	/**
	 * XML Parser
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "xmlparser", method = RequestMethod.POST)
	public @ResponseBody String xmlparser(MultipartHttpServletRequest request) throws Exception {
		List<MultipartFile> files = request.getFiles("xmlFile");
		String path = FileUtil.uploadFile(files.get(0), UPLOAD_PATH_PRESS, false);
		return new String(XmlParser.createHtmls(UPLOAD_PATH_PRESS, UPLOAD_PATH_PRESS + path).getBytes("UTF-8"), "ISO8859-1");
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping("del/{id:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		press = pressService.findById(id);
		pressService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");

		// 删除静态文件
		File f_html = new File(new StringBuffer(UPLOAD_PATH_PRESS).append(id).append(".html").toString());
		if (f_html.exists()) {
			f_html.delete();
		}

		// 删除Logo图片
		File f_logo = new File(new StringBuffer(UPLOAD_PATH_PRESS).append(press.getPressLogo()).toString());
		if (f_logo.exists()) {
			f_logo.delete();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pressList", pressService.findAllList("PressOrderby"));
		map.put("press", press);
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/manage/press";
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws Exception
	 */
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		switch (((Press) map.get("press")).getPressLocation()) {
		case 1:
			FileUtil.generateHTML("WEB-INF/ftl/press", "Press.ftl", "press_chinese.html", map, request.getSession().getServletContext(), UPLOAD_PATH_PRESS);
			break;
		case 2:
			FileUtil.generateHTML("WEB-INF/ftl/press", "Press.ftl", "press_english.html", map, request.getSession().getServletContext(), UPLOAD_PATH_PRESS);
			break;
		case 3:
			FileUtil.generateHTML("WEB-INF/ftl/press", "Press.ftl", "press_journal.html", map, request.getSession().getServletContext(), UPLOAD_PATH_PRESS);
			break;
		default:
			break;
		}
	}

}
