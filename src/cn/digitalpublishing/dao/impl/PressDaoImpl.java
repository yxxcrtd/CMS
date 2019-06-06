package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.PressDao;
import cn.digitalpublishing.domain.Press;

/**
 * Press Dao Implement
 */
@Repository
public class PressDaoImpl extends BaseDaoImpl<Press, Integer, String, String>implements PressDao {

	@Override
	public List<Press> findListByLocation(int pressLocation) {
		// SELECT * FROM "T_Press" WHERE "PressLocation" = ? AND ROWNUM <= 8 ORDER BY "PressOrderby"
		String sql = "SELECT * FROM (SELECT * FROM \"T_Press\" WHERE \"PressLocation\" = ? AND \"PressVisible\" = 0 ORDER BY \"PressOrderby\") ORDER BY ROWNUM";
		LOGGER.info(sql + " : " + pressLocation);
		List<Press> pressList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Press.class), new Object[] { pressLocation });
		return 0 == pressList.size() ? null : pressList;
	}

}
