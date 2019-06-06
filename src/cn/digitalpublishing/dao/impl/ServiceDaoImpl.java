package cn.digitalpublishing.dao.impl;

import org.springframework.stereotype.Repository;

import cn.digitalpublishing.dao.ServiceDao;
import cn.digitalpublishing.domain.Service;

/**
 * Help Dao Implement
 */
@Repository
public class ServiceDaoImpl extends BaseDaoImpl<Service, Integer, String, String> implements ServiceDao {

}
