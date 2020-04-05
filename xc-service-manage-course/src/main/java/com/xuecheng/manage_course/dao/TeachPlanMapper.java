package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author code 2020/4/5
 */
@Mapper
public interface TeachPlanMapper {
    public TeachplanNode selectList(String courseId);
}
