package cn.digitalpublishing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.NewsDao;
import cn.digitalpublishing.domain.News;
import cn.digitalpublishing.service.NewsService;

/**
 * News Service Implement
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News, Integer> implements NewsService {

	@Override
	public List<News> findListByOrderby() {
		return ((NewsDao) baseDao).findListByOrderby();
	}

}
