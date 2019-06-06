package cn.digitalpublishing.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.Advertisement;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;
import freemarker.template.TemplateException;

/**
 * Advertisement Controller
 */
@Controller
@RequestMapping("manage/advertisement")
public class AdvertisementController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "p", required = true, defaultValue = "1") int p) {
		ModelAndView mav = new ModelAndView();
		Pager pager = new Pager();
		pager.setPageNo(p);
		int count = advertisementService.findAllCount(advertisement);
		pager.setTotalCount(count);
		mav.addObject("adList", advertisementService.findByPager(pager, "", "\"AdvertisementLocation\", \"AdvertisementOrderby\""));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.setViewName("advertisement/AdvertisementList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param advertisementId
	 * @return
	 */
	@RequestMapping("edit/{advertisementId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "advertisementId") int advertisementId) {
		ModelAndView mav = new ModelAndView();
		if (0 == advertisementId) {
			advertisement = new Advertisement();
			advertisement.setAdvertisementId(advertisementId);
			advertisement.setAdvertisementVisible(INT_ZERO);
		} else {
			advertisement = advertisementService.findById(advertisementId);
		}
		mav.addObject("visibleMap", getVisibleMap());
		mav.addObject("typeMap", getTypeMap());
		mav.addObject("locationMap", getLocationMap());
		mav.addObject("advertisement", advertisement);
		mav.setViewName("advertisement/AdvertisementEdit");
		return mav;
	}

	/**
	 * Save
	 * 
	 * @param advertisement
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("advertisement") @Valid Advertisement advertisement, BindingResult result, final RedirectAttributes redirectAttributes, MultipartHttpServletRequest request) {
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("typeMap", getTypeMap());
			mav.addObject("locationMap", getLocationMap());
			mav.addObject("advertisement", advertisement);
			mav.setViewName("advertisement/AdvertisementEdit");
			return mav;
		}

		List<MultipartFile> files = request.getFiles("file");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				advertisement.setAdvertisementPicture(FileUtil.uploadFile(file, UPLOAD_PATH_AD, false));
			}
		}

		List<MultipartFile> filesAttachment = request.getFiles("attachment");
		for (MultipartFile file : filesAttachment) {
			if (!file.isEmpty()) {
				advertisement.setAdvertisementAttachment(FileUtil.uploadFile(file, UPLOAD_PATH_AD, true));
			}
		}

		if (0 == advertisement.getAdvertisementId()) {
			advertisementService.save(advertisement);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			advertisementService.update(advertisement);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertisement", advertisement);
		map.put("advertisementList", advertisementService.findListByLocation(advertisement.getAdvertisementLocation()));
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/manage/advertisement");
	}

	/**
	 * Delete
	 * 
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("del/{id:[\\d]+}")
	public String delete(@PathVariable(value = "id") int id, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		advertisementService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("advertisement", advertisement);
		map.put("advertisementList", advertisementService.findListByLocation(advertisement.getAdvertisementLocation()));
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/manage/advertisement";
	}

	private Map<String, String> getVisibleMap() {
		Map<String, String> visibleMap = new LinkedHashMap<String, String>();
		visibleMap.put("0", "开启");
		visibleMap.put("1", "关闭");
		return visibleMap;
	}

	private Map<String, String> getTypeMap() {
		Map<String, String> typeMap = new LinkedHashMap<String, String>();
		typeMap.put("1", "图片广告");
		typeMap.put("2", "文字广告");
		typeMap.put("3", "代码广告");
		return typeMap;
	}

	private Map<String, String> getLocationMap() {
		Map<String, String> locationMap = new LinkedHashMap<String, String>();
		locationMap.put("1", "首页顶部");
		locationMap.put("2", "首页底部");
		locationMap.put("3", "二级中文");
		locationMap.put("4", "二级英文");
		locationMap.put("5", "二级期刊");
		locationMap.put("6", "Elsevier");
		return locationMap;
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		switch (((Advertisement) map.get("advertisement")).getAdvertisementLocation()) {
		case 1:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Advertisement.ftl", "ad_index_top.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		case 2:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Advertisement.ftl", "ad_index_bottom.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		case 3:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Advertisement.ftl", "ad_chinese.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		case 4:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Advertisement.ftl", "ad_english.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		case 5:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Advertisement.ftl", "ad_journal.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		case 6:
			FileUtil.generateHTML("WEB-INF/ftl/advertisement", "Elsevier.ftl", "elsevier.html", map, request.getSession().getServletContext(), UPLOAD_PATH_AD);
			break;
		default:
			break;
		}
	}

}
