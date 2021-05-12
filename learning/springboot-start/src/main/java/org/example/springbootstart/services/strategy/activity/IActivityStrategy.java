package org.example.springbootstart.services.strategy.activity;

import org.example.springbootstart.dtos.param.activity.ActivityClassListDto;

import java.util.List;

/**
 * 活动策略接口
 */
public interface IActivityStrategy {
    /**
     * 获取活动信息和班级列表
     * @param number 活动编号
     * @param type 活动类型 type = 1/2/3 详细见 {@link org.example.springbootstart.services.enums.ActivityTypeEnum}
     * @return
     */
    List<ActivityClassListDto> getActivityClassList(long number, int type);
}
