package cn.digitalpublishing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.AdvertisementDao;
import cn.digitalpublishing.domain.Advertisement;
import cn.digitalpublishing.service.AdvertisementService;

/**
 * Advertisement Service Implement
 */
@Service
public class AdvertisementServiceImpl extends BaseServiceImpl<Advertisement, Integer> implements AdvertisementService {

	@Override
	public List<Advertisement> findListByLocation(int pressLocation) {
		return ((AdvertisementDao) baseDao).findListByLocation(pressLocation);
	}

}
