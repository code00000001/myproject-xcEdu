package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_course.dao.CourseBaseRepository;
import com.xuecheng.manage_course.dao.TeachPlanMapper;
import com.xuecheng.manage_course.dao.TeachPlanRespository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author code 2020/4/5
 */
@Service
public class CourseService {
    @Autowired
    private TeachPlanMapper teachPlanMapper;
    @Autowired
    private CourseBaseRepository courseBaseRepository;
    @Autowired
    private TeachPlanRespository teachPlanRespository;

    /**
     * 查询课程计划
     * @param courseId
     * @return
     */
    public List<TeachplanNode> findTeachPlanList(String courseId){
        return teachPlanMapper.selectList(courseId);
    }


    /**
     * 添加课程计划
     * @param teachplan
     * @return
     */
    @Transactional
    public ResponseResult addTeachplan(Teachplan teachplan) {
        //校验课程id和课程计划名称
        if (teachplan == null
            || StringUtils.isBlank(teachplan.getCourseid())
            || StringUtils.isBlank(teachplan.getPname())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        //取出课程id
        String courseid = teachplan.getCourseid();
        //取出父节点id
        String parentid = teachplan.getParentid();
        if (StringUtils.isEmpty(parentid)){
            //如果父节点为空，则取出根节点
            parentid = getTeachplanRoot(courseid);
        }
        //取出父节点信息
        Optional<Teachplan> optional = teachPlanRespository.findById(parentid);
        if (optional == null){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        Teachplan teachplanParent = optional.get();
        //父节点级别
        String parentGrade = teachplanParent.getGrade();
        //设置子节点级别，根据父节点来判断
        if ("1".equals(parentGrade)){
            teachplan.setGrade("2");
        } else if ("2".equals(parentGrade)){
            teachplan.setGrade("3");
        }
        //设置父节点
        teachplan.setParentid(parentid);
        //设置发布状态
        teachplan.setStatus("0");
        //设置课程id
        teachplan.setCourseid(courseid);
        //保存课程
        teachPlanRespository.save(teachplan);
        return new ResponseResult(CommonCode.SUCCESS);

    }

    private String getTeachplanRoot(String courseid){
        //校验课程id
        Optional<CourseBase> optional = courseBaseRepository.findById(courseid);
        if (optional ==null){
            return null;
        }
        //取出课程计划根节点
        List<Teachplan> teachplanList = teachPlanRespository.findByCourseidAndParentid(courseid, "0");
        CourseBase courseBase = optional.get();
        if (teachplanList == null || teachplanList.size()==0){
            //新增一个根节点
            Teachplan teachplanRoot = new Teachplan();
            teachplanRoot.setCourseid(courseid);
            teachplanRoot.setGrade("1");
            teachplanRoot.setParentid("0");
            teachplanRoot.setStatus("0");
            teachplanRoot.setPname(courseBase.getName());
            teachPlanRespository.save(teachplanRoot);
            return teachplanRoot.getId();
        }
        return teachplanList.get(0).getId();
    }
}
