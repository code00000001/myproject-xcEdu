package com.xuecheng.cms_manage_client.service;

import com.alibaba.fastjson.JSON;
import com.xuecheng.cms_manage_client.dao.CmsPageRepository;
import com.xuecheng.framework.domain.cms.CmsPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * 消费方监听消息发布队列，收到消息后从mongodb下载页面保存到服务器
 */
@Component
public class ConsumerPostPage {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);
    @Autowired
    private CmsPageRepository cmsPageRepository;
    @Autowired
    private PageService pageService;
    public void postPage(String msg){
        //解析json消息
        Map map = JSON.parseObject(msg, Map.class);
        LOGGER.info("receive cms post page:{}",msg.toString());
        //取出页面id
        String pageId = (String) map.get("pageId");
        //查询页面信息
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (!optional.isPresent()){
            LOGGER.error("receive cms post page, cmsPage is null:{}",msg.toString());
            return;
        }
        //调用service把页面保存到服务器物理路径
        pageService.savePageToServerPath(pageId);
    }
}
