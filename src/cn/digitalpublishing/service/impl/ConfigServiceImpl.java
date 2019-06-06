package cn.digitalpublishing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.ConfigDao;
import cn.digitalpublishing.domain.Config;
import cn.digitalpublishing.service.ConfigService;

/**
 * Config Service Implement
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config, Integer>implements ConfigService {

	@Override
	public List<Config> findListByConfigType(String type) {
		return ((ConfigDao) baseDao).findListByConfigType(type);
	}

	@Override
	public List<Config> findListByConfigType(int visible, String type) {
		return ((ConfigDao) baseDao).findListByConfigType(visible, type);
	}

}
