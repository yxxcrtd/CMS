package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.DynamicDao;
import cn.digitalpublishing.domain.Dynamic;

/**
 * Dynamic Dao Implement
 */
@Repository
public class DynamicDaoImpl extends BaseDaoImpl<Dynamic, Integer, String, String> implements DynamicDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.DynamicDao#findByDynamicObj(int)
	 */
	@Override
	public Dynamic findByDynamicObj(int obj) {
		String sql = new StringBuffer("SELECT * FROM \"T_Dynamic\" WHERE \"DynamicObj\"= ?").toString();
		LOGGER.info(sql + " : " + obj);
		List<Dynamic> dynamicList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Dynamic.class), obj);
		return 0 == dynamicList.size() ? null : dynamicList.get(0);
	}

}
