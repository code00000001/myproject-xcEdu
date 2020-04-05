package com.xuecheng.manage_course.controller;

import com.xuecheng.api.course.CourseControllerApi;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.manage_course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author code 2020/4/5
 */
@RestController
@RequestMapping("/course")
public class CourseController implements CourseControllerApi {
    @Autowired
    private CourseService courseService;


    @Override
    @GetMapping("/teachplan/list/{courseId}")
    public List<TeachplanNode> findTeachPlanList(@PathVariable("courseId") String courseId) {
        return courseService.findTeachPlanList(courseId);
    }
}
