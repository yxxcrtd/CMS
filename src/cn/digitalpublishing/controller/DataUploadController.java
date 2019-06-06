package cn.digitalpublishing.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("manage/upload")
public class DataUploadController extends BaseController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView upload(HttpServletRequest request) {
		FileInputStream is = null;
		FileOutputStream os = null;
		try {
			FileInputStream fis = new FileInputStream(new File("/apphome/update.xlsx"));
			XSSFWorkbook xwb = new XSSFWorkbook(fis);
			XSSFSheet sheet = xwb.getSheetAt(0);
			for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
				XSSFRow row = sheet.getRow(i);
				if (row != null) {
					XSSFCell src = row.getCell(0);
					XSSFCell dest = row.getCell(1);

					System.out.println("第" + i + "行，源文件路径：" + src + " - 目标文件路径：" + dest);

					try {
						is = new FileInputStream(new File(src.toString()));
						os = new FileOutputStream("/filestore" + dest.toString());
						byte[] bytesRead = new byte[1024];
						int length = 0;
						while (0 < (length = is.read(bytesRead))) {
							os.write(bytesRead, 0, length);
						}
						is.close();
						os.flush();
						os.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
