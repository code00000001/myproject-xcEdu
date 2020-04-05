package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

/**
 * @author code
 */
public interface CourseControllerApi {
    /**
     * @param courseId
     * @return
     */
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachPlanList(String courseId);

    @ApiOperation("添加课程计划")
    public ResponseResult addTeachPlan(Teachplan teachplan);
}
