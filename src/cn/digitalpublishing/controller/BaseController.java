package cn.digitalpublishing.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import cn.digitalpublishing.domain.Advertisement;
import cn.digitalpublishing.domain.Config;
import cn.digitalpublishing.domain.Dynamic;
import cn.digitalpublishing.domain.News;
import cn.digitalpublishing.domain.Press;
import cn.digitalpublishing.domain.Service;
import cn.digitalpublishing.service.AdvertisementService;
import cn.digitalpublishing.service.ConfigService;
import cn.digitalpublishing.service.DynamicService;
import cn.digitalpublishing.service.NewsService;
import cn.digitalpublishing.service.PressService;
import cn.digitalpublishing.service.ServiceService;
import cn.digitalpublishing.util.Pager;

/** Base Controller */
public class BaseController {

	/** Log */
	protected static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

	/** int 0 */
	protected static final int INT_ZERO = 0;

	/** int 1 */
	protected static final int INT_ONE = 1;

	/** int 8 */
	protected static final int INT_EIGHT = 8;

	/** menu */
	protected static final String STRING_MENU = "menu";

	/** news */
	protected static final String STRING_NEWS = "news";

	/** Store Advertisement Images */
	@Value("${upload.path.ad}")
	protected String UPLOAD_PATH_AD;

	/** Store Press XML & Image */
	@Value("${upload.path.press}")
	protected String UPLOAD_PATH_PRESS;

	/** Store Institution LOGO */
	@Value("${upload.path.logo}")
	protected String UPLOAD_PATH_LOGO;

	/** Store News Upload Images */
	@Value("${upload.path.news}")
	protected String UPLOAD_PATH_NEWS;

	/** Pager Object */
	protected Pager pager;

	/** News Object */
	protected News news;

	/** Menu Object */
	protected Config menu;

	/** Dynamic Object */
	protected Dynamic dynamic;

	/** Service Object */
	protected Service service;

	/** Press Object */
	protected Press press;

	/** Advertisement Object */
	protected Advertisement advertisement;

	/** News Service */
	@Autowired
	protected NewsService newsService;

	/** Menu Service */
	@Autowired
	protected ConfigService configService;

	/** Dynamic Service */
	@Autowired
	protected DynamicService dynamicService;

	/** Service Service */
	@Autowired
	protected ServiceService serviceService;

	/** Press Service */
	@Autowired
	protected PressService pressService;

	/** Advertisement Service */
	@Autowired
	protected AdvertisementService advertisementService;

}
