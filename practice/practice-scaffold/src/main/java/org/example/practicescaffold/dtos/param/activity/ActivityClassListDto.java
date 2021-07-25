package org.example.practicescaffold.dtos.param.activity;

import java.util.List;

import lombok.Data;

/**
 * 活动列表接口
 */
@Data
public class ActivityClassListDto {
    private long activityNumber;
    private String activityName;
    private int activityStatus;
    private String activityBeginTime;
    private String activityEndTime;

    private List<ClassListDto> classList;
}
