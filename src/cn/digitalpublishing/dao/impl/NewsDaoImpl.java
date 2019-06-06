package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.NewsDao;
import cn.digitalpublishing.domain.News;

/**
 * Category Dao Implement
 */
@Repository
public class NewsDaoImpl extends BaseDaoImpl<News, Integer, String, String> implements NewsDao {

	@Override
	public List<News> findListByOrderby() {
		String sql = "SELECT * FROM (SELECT * FROM \"T_News\" ORDER BY \"NewsOrderby\" DESC, \"NewsCreateTime\" DESC) ORDER BY ROWNUM";
		LOGGER.info(sql);
		List<News> newsList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(News.class), new Object[] {});
		return 0 == newsList.size() ? null : newsList;
	}

}
