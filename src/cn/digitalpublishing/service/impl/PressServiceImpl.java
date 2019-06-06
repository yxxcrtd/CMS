package cn.digitalpublishing.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.digitalpublishing.dao.PressDao;
import cn.digitalpublishing.domain.Press;
import cn.digitalpublishing.service.PressService;

/** Press Service Implement */
@Service
public class PressServiceImpl extends BaseServiceImpl<Press, Integer>implements PressService {

	@Override
	public List<Press> findListByLocation(int pressLocation) {
		return ((PressDao) baseDao).findListByLocation(pressLocation);
	}

}
