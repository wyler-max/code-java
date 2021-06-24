package org.example.practicescaffold.controller;

import java.util.List;

import org.example.practicescaffold.dtos.param.activity.ActivityClassListDto;
import org.example.practicescaffold.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/activity")
@Description(value = "测试Controller")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    /**
     * 用于测试策略模式
     */
    @ApiOperation(value = "获取活动的班级列表")
    @GetMapping(value = "/getActivityClassList")
    public List<ActivityClassListDto> getActivityClassList(@RequestParam("activityNumber") long activityNumber,
        @RequestParam("activityType") int activityType) {
        return activityService.getActivityClassList(activityNumber, activityType);
    }
}
