package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.domain.News;

/**
 * News Service Interface
 */
public interface NewsService extends BaseService<News, Integer> {
	
	List<News> findListByOrderby();

}
