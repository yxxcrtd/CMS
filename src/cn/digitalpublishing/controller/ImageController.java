package cn.digitalpublishing.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cn.digitalpublishing.util.FileUtil;

/**
 * Image Controller
 */
@Controller
@RequestMapping("manage")
public class ImageController extends BaseController {

	/**
	 * Image Upload
	 * 
	 * @return
	 */
	@RequestMapping(value = "image", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request) {
		JSONObject obj = new JSONObject();
		String imageName = "";
		List<MultipartFile> files = request.getFiles("imgFile");
		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				imageName = FileUtil.uploadFile(file, UPLOAD_PATH_NEWS, false);
			}
		}
		obj.put("error", 0);
		obj.put("url", new StringBuffer().append("/upload/image/").append(imageName).toString());
		return obj.toJSONString();
	}

}
