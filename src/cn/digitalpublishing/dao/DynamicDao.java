package cn.digitalpublishing.dao;

import cn.digitalpublishing.domain.Dynamic;

/**
 * Dynamic DAO
 */
public interface DynamicDao extends BaseDao<Dynamic, Integer> {
	
	Dynamic findByDynamicObj(int obj);

}
