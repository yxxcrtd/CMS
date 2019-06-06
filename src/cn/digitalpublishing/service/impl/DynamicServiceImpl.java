package cn.digitalpublishing.service.impl;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.DynamicDao;
import cn.digitalpublishing.domain.Dynamic;
import cn.digitalpublishing.service.DynamicService;

/**
 * Dynamic Service Implement
 */
@Service
public class DynamicServiceImpl extends BaseServiceImpl<Dynamic, Integer> implements DynamicService {
	
	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.service.DynamicService#findByDynamicObj(int)
	 */
	@Override
	public Dynamic findByDynamicObj(int obj) {
		return ((DynamicDao) baseDao).findByDynamicObj(obj);
	}

}
