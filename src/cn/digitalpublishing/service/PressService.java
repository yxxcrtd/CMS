package cn.digitalpublishing.service;

import java.util.List;

import cn.digitalpublishing.domain.Press;

/** Press Service Interface */
public interface PressService extends BaseService<Press, Integer> {

	List<Press> findListByLocation(int pressLocation);

}
