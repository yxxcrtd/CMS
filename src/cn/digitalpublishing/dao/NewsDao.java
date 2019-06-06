package cn.digitalpublishing.dao;

import java.util.List;

import cn.digitalpublishing.domain.News;

/**
 * News DAO
 */
public interface NewsDao extends BaseDao<News, Integer> {
	
	List<News> findListByOrderby();

}
