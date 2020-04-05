package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author code 2020/4/5
 */
@Mapper
public interface TeachPlanMapper {
    public List<TeachplanNode> selectList(String courseId);
}
