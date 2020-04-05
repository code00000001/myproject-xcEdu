package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.manage_course.dao.TeachPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author code 2020/4/5
 */
@Service
public class CourseService {
    @Autowired
    private TeachPlanMapper teachPlanMapper;


    /**
     * 查询课程计划
     * @param courseId
     * @return
     */
    public List<TeachplanNode> findTeachPlanList(String courseId){
        return teachPlanMapper.selectList(courseId);
    }
}
