package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll(){
        //模糊匹配
        ExampleMatcher matcher = ExampleMatcher.matching();
        matcher = matcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        //精确条件值
        CmsPage cmsPage = new CmsPage();
        //站点id
        cmsPage.setSiteId("5a751fab6abb5044e0d19ea1");
        //模板id
        cmsPage.setTemplateId("5a962b52b00ffc514038faf7");
        //创建条件实例
        Example example = Example.of(cmsPage, matcher);
        Pageable pageable = new PageRequest(0, 10);
        Page<CmsPage> all = cmsPageRepository.findAll(example,pageable);
        System.out.println(all);
    }

    //修改
    @Test
    public void testUpdate(){
        Optional<CmsPage> cmsPageOptional = cmsPageRepository.findById("5abefd525b05aa293098fca6");
        if (cmsPageOptional.isPresent()){
            CmsPage cmsPage = cmsPageOptional.get();
            cmsPage.setPageAliase("cc");
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }
    }

    //自定义分页查询接口测试
    @Test
    public void testFindBySiteIdAndPageType(){
        Pageable pageable = PageRequest.of(0, 2);
        Page<CmsPage> bySiteIdAndPageType = cmsPageRepository.findBySiteIdAndPageType("5a751fab6abb5044e0d19ea1", "1", pageable);
        System.out.println(bySiteIdAndPageType.toString());
    }
}
