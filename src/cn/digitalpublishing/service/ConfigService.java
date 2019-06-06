package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.domain.Config;

/**
 * Config Service Interface
 */
public interface ConfigService extends BaseService<Config, Integer> {

	List<Config> findListByConfigType(String type);

	List<Config> findListByConfigType(int visible, String type);

}
