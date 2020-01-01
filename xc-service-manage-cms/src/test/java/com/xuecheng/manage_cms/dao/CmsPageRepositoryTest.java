package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmsPageRepositoryTest {
    @Autowired
    CmsPageRepository cmsPageRepository;

    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
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
