package org.example.springbootstart.controller;

import org.example.springbootstart.dtos.param.activity.ActivityClassListDto;
import org.example.springbootstart.services.ActivityServices;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {

    @Autowired
    ActivityServices activityServices;

    @ApiOperation(value = "获取活动的班级列表")
    @GetMapping(value = "/getActivityClassList")
    public List<ActivityClassListDto> getActivityClassList(@RequestParam("activityNumber") long activityNumber,
                                                           @RequestParam("activityType") int activityType) {
        return activityServices.getActivityClassList(activityNumber, activityType);
    }
}
