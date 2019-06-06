package cn.digitalpublishing.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.digitalpublishing.domain.News;
import cn.digitalpublishing.util.FileUtil;
import cn.digitalpublishing.util.Pager;
import freemarker.template.TemplateException;

/**
 * News Controller
 */
@Controller
@RequestMapping("manage/news")
public class NewsController extends BaseController {

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
		int count = newsService.findAllCount(news);
		pager.setTotalCount(count);
		mav.addObject("newsList", newsService.findByPager(pager));
		mav.addObject("pager", pager);
		mav.addObject("count", count);
		mav.setViewName("news/NewsList");
		return mav;
	}

	/**
	 * Edit
	 * 
	 * @param newsId
	 * @return
	 */
	@RequestMapping("edit/{newsId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "newsId") int newsId) {
		if (0 == newsId) {
			news = new News();
			news.setNewsId(newsId);
			news.setNewsCreateTime(new Date());
			news.setNewsOrderby(INT_ONE);
		} else {
			news = newsService.findById(newsId);
		}
		return new ModelAndView("news/NewsEdit", "news", news);
	}

	/**
	 * Save
	 * 
	 * @param news
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("news") @Valid News news, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("news/NewsEdit", "news", news);
		}

		// 新增父分类或子分类
		if (0 == news.getNewsId()) {
			newsService.save(news);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			newsService.update(news);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newsList", newsService.findListByOrderby());
		map.put("request", request);
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/manage/news");
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
		newsService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");

		File f = new File(new StringBuffer(UPLOAD_PATH_NEWS).append(id).append(".html").toString());
		if (f.exists()) {
			f.delete();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("newsList", newsService.findAllList(""));
		try {
			generateHTML(map, request);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/manage/news";
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	@SuppressWarnings("unchecked")
	protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
		FileUtil.generateHTML("WEB-INF/ftl/news", "News.ftl", "news.html", map, request.getSession().getServletContext(), UPLOAD_PATH_NEWS);
		for (News n : (List<News>) map.get("newsList")) {
			map.put("news", n);
			FileUtil.generateHTML("WEB-INF/ftl/news", "NewsDetails.ftl", n.getNewsId() + ".html", map, request.getSession().getServletContext(), UPLOAD_PATH_NEWS);
		}
	}

}
