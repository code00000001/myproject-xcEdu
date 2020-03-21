package com.xuecheng.cms_manage_client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 查询站点信息，主要获取站点物理路径
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
}
