package cn.digitalpublishing.dao;

import java.util.List;

import cn.digitalpublishing.domain.Press;

/** Press DAO */
public interface PressDao extends BaseDao<Press, Integer> {

	List<Press> findListByLocation(int pressLocation);

}
