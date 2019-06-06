package cn.digitalpublishing.controller;

import java.io.IOException;
import java.util.List;

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

import cn.digitalpublishing.domain.Service;
import freemarker.template.TemplateException;

/**
 * Service Controller
 */
@Controller
@RequestMapping("manage/service")
public class ServiceController extends BaseController {

	/**
	 * List
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView list() {
		return new ModelAndView("service/ServiceList", "serviceList", serviceService.findAllList("ServiceOrderby"));
	}

	/**
	 * Edit
	 * 
	 * @param serviceId
	 * @return
	 */
	@RequestMapping("edit/{serviceId:[\\d]+}")
	public ModelAndView edit(@PathVariable(value = "serviceId") int serviceId) {
		if (0 == serviceId) {
			service = new Service();
			service.setServiceId(serviceId);
			service.setServiceOrderby(INT_ONE);
		} else {
			service = serviceService.findById(serviceId);
		}
		return new ModelAndView("service/ServiceEdit", "service", service);
	}

	/**
	 * Save
	 * 
	 * @param service
	 * @param redirectAttributes
	 * @return
	 * @throws TemplateException
	 * @throws IOException
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("service") @Valid Service service, BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (result.hasErrors()) {
			return new ModelAndView("service/ServiceEdit", "service", service);
		}

		if (0 == service.getServiceId()) {
			serviceService.save(service);
			redirectAttributes.addFlashAttribute("tips", "保存成功！");
		} else {
			serviceService.update(service);
			redirectAttributes.addFlashAttribute("tips", "修改成功！");
		}

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("serviceList", serviceService.findAllList("ServiceOrderby"));
		// map.put("request", request);
		// try {
		// generateHTML(map, request);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		writeRedis(service);

		return new ModelAndView("redirect:/manage/service");
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
		serviceService.delete(id);
		redirectAttributes.addFlashAttribute("tips", "删除成功！");

		// File f_zh_CN = new File(new StringBuffer(UPLOAD_PATH_SERVICE).append(id).append("_zh_CN.html").toString());
		// if (f_zh_CN.exists()) {
		// f_zh_CN.delete();
		// }
		serviceService.del(id + "_zh_CN.html");
		// File f_en_US = new File(new StringBuffer(UPLOAD_PATH_SERVICE).append(id).append("_en_US.html").toString());
		// if (f_en_US.exists()) {
		// f_en_US.delete();
		// }
		serviceService.del(id + "_en_US.html");

		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("serviceList", serviceService.findAllList("ServiceOrderby"));
		// map.put("service", service);
		// try {
		// generateHTML(map, request);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		writeRedis(serviceService.findById(id));

		return "redirect:/manage/service";
	}

	/**
	 * Generate HTML File
	 * 
	 * @param map
	 * @param request
	 * @throws IOException
	 * @throws TemplateException
	 */
	// @SuppressWarnings("unchecked")
	// protected void generateHTML(Map<String, Object> map, HttpServletRequest request) throws Exception {
	// FileUtil.generateHTML("WEB-INF/ftl/service", "Service_zh_CN.ftl", "service_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_SERVICE);
	// FileUtil.generateHTML("WEB-INF/ftl/service", "Service_en_US.ftl", "service_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_SERVICE);
	// for (Service s : (List<Service>) map.get("serviceList")) {
	// map.put("service", s);
	// FileUtil.generateHTML("WEB-INF/ftl/service", "ServiceDetails_zh_CN.ftl", s.getServiceId() + "_zh_CN.html", map, request.getSession().getServletContext(), UPLOAD_PATH_SERVICE);
	// FileUtil.generateHTML("WEB-INF/ftl/service", "ServiceDetails_en_US.ftl", s.getServiceId() + "_en_US.html", map, request.getSession().getServletContext(), UPLOAD_PATH_SERVICE);
	// }
	// }
	private void writeRedis(Service s) {
		serviceService.del("service_zh_CN");
		serviceService.del("service_en_US");
		serviceService.del("service_d_zh_CN");
		serviceService.del("service_d_en_US");
		List<Service> list = serviceService.findAllList("ServiceOrderby");
		for (int i = 0; i < list.size(); i++) {
			String cc = list.get(i).getServiceChineseContent().replaceAll("<([^>]*)>", "").replaceAll("[\\t\\n\\r]", "").replace(" ", "");
			serviceService.zadd("service_zh_CN", (i + 1), "<p><a href='/service/" + list.get(i).getServiceId() + "'>" + list.get(i).getServiceChineseTitle() + "</a><br /><span>" + (91 < cc.length() ? cc.substring(0, 91) + "..." : cc) + "</span></p>");
			String ce = list.get(i).getServiceEnglishContent().replaceAll("<([^>]*)>", "").replaceAll("[\\t\\n\\r]", "");//.replace(" ", "");
			serviceService.zadd("service_en_US", (i + 1), "<p class='wrap'><a href='/service/" + list.get(i).getServiceId() + "'>" + list.get(i).getServiceEnglishTitle() + "</a><br /><span>" + (192 < ce.length() ? ce.substring(0, 191) + "..." : ce) + "</span></p>");
			serviceService.zadd("service_d_zh_CN", (i + 1), list.get(i).getServiceId() + "@@@@@id@@@@@" + list.get(i).getServiceChineseTitle() + "@@@@@@@@@@<div class='perRight'><h1>" + list.get(i).getServiceChineseTitle() + "</h1>" + list.get(i).getServiceChineseContent() + "</div>");
			serviceService.zadd("service_d_en_US", (i + 1), list.get(i).getServiceId() + "@@@@@id@@@@@" + list.get(i).getServiceEnglishTitle() + "@@@@@@@@@@<div class='perRight'><h1>" + list.get(i).getServiceEnglishTitle() + "</h1>" + list.get(i).getServiceEnglishContent() + "</div>");
		}
	}

}
