package cn.digitalpublishing.service;

import cn.digitalpublishing.domain.Dynamic;

/**
 * Dynamic Service Interface
 */
public interface DynamicService extends BaseService<Dynamic, Integer> {
	
	Dynamic findByDynamicObj(int obj);

}
