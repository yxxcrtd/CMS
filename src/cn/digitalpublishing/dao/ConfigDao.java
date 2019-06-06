package cn.digitalpublishing.dao;

import java.util.List;

import cn.digitalpublishing.domain.Config;

/**
 * Config DAO
 */
public interface ConfigDao extends BaseDao<Config, Integer> {

	List<Config> findListByConfigType(String type);

	List<Config> findListByConfigType(int visible, String type);

}
