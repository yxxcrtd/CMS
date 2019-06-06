package cn.digitalpublishing.dao;

import java.util.List;

import cn.digitalpublishing.domain.Advertisement;

/**
 * Advertisement DAO
 */
public interface AdvertisementDao extends BaseDao<Advertisement, Integer> {

	List<Advertisement> findListByLocation(int pressLocation);

}
