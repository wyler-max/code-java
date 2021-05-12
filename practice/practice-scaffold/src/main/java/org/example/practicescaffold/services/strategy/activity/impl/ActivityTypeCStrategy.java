package org.example.practicescaffold.services.strategy.activity.impl;

import org.example.practicescaffold.dtos.param.activity.ActivityClassListDto;
import org.example.practicescaffold.services.strategy.activity.IActivityStrategy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * C类型活动策略
 */
@Component
public class ActivityTypeCStrategy implements IActivityStrategy {
    @Override
    public List<ActivityClassListDto> getActivityClassList(long number, int type) {
        System.out.println("ActivityTypeCStrategy: number=" + number +", type="+type);
        return Collections.emptyList();
    }
}
