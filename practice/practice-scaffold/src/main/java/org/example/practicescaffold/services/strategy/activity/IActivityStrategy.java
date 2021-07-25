package org.example.practicescaffold.services.strategy.activity;

import java.util.List;

import org.example.practicescaffold.dtos.param.activity.ActivityClassListDto;
import org.example.practicescaffold.services.enums.ActivityTypeEnum;

/**
 * 活动策略接口
 */
public interface IActivityStrategy {
    /**
     * 获取活动信息和班级列表
     *
     * @param number
     *            活动编号
     * @param type
     *            活动类型 type = 1/2/3 详细见 {@link ActivityTypeEnum}
     * @return
     */
    List<ActivityClassListDto> getActivityClassList(long number, int type);
}
