package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * @author code
 */
public interface CourseControllerApi {
    /**
     * @param courseId
     * @return
     */
    @ApiOperation("程序计划查询")
    public List<TeachplanNode> findTeachPlanList(String courseId);
}
