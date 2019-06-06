package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.domain.Advertisement;

/**
 * Advertisement Service Interface
 */
public interface AdvertisementService extends BaseService<Advertisement, Integer> {

	List<Advertisement> findListByLocation(int pressLocation);

}
