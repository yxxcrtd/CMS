package cn.digitalpublishing.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.AdvertisementDao;
import cn.digitalpublishing.domain.Advertisement;

/**
 * Advertisement Dao Implement
 */
@Repository
public class AdvertisementDaoImpl extends BaseDaoImpl<Advertisement, Integer, String, String> implements AdvertisementDao {

	/* (non-Javadoc)
	 * 
	 * @see cn.digitalpublishing.dao.AdvertisementDao#findListByLocation(int, int)
	 */
	@Override
	public List<Advertisement> findListByLocation(int pressLocation) {
		String sql = "SELECT * FROM (SELECT * FROM \"T_Advertisement\" WHERE \"AdvertisementLocation\" = ? AND \"AdvertisementVisible\" = 0 ORDER BY \"AdvertisementOrderby\") ORDER BY ROWNUM";
		LOGGER.info(sql + " : " + pressLocation);
		List<Advertisement> pressList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Advertisement.class), new Object[] { pressLocation });
		return 0 == pressList.size() ? null : pressList;
	}

	// SELECT * FROM (SELECT t.*, ROWNUM r FROM (SELECT * FROM "T_Advertisement" ORDER BY "AdvertisementId" DESC) t WHERE ROWNUM <= ?) WHERE r > ? : 10 , 0

}
