package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author code 2020/4/5
 */
public interface TeachPlanRespository extends JpaRepository<Teachplan,String> {
    /**
     * 根据课程id和父节点id查询出节点列表，可以使用此方法实现查询根节点
     * @param courseId
     * @param parentId
     * @return
     */
    public List<Teachplan> findByCourseidAndParentid(String courseId, String parentId);
}
