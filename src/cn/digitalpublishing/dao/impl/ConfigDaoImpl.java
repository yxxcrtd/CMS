package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ConfigDao;
import cn.digitalpublishing.domain.Config;

/**
 * Config Dao Implement
 */
@Repository
public class ConfigDaoImpl extends BaseDaoImpl<Config, Integer, String, String>implements ConfigDao {

	@Override
	public List<Config> findListByConfigType(String type) {
		String sql = "SELECT * FROM \"T_Config\" WHERE \"ConfigType\" = ?";
		LOGGER.info(sql + " : " + type);
		List<Config> configList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Config.class), new Object[] { type });
		return 0 == configList.size() ? null : configList;
	}

	@Override
	public List<Config> findListByConfigType(int visible, String type) {
		String sql = "SELECT * FROM \"T_Config\" WHERE \"ConfigVisible\" = ? AND \"ConfigType\" = ?";
		LOGGER.info(sql + " : " + visible + " - " + type);
		List<Config> configList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Config.class), new Object[] { visible, type });
		return 0 == configList.size() ? null : configList;
	}

}
