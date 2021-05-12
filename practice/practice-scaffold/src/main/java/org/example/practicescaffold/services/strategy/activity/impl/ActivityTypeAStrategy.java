package org.example.practicescaffold.services.strategy.activity.impl;

import org.example.practicescaffold.dtos.param.activity.ActivityClassListDto;
import org.example.practicescaffold.services.strategy.activity.IActivityStrategy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * A类型活动策略
 */
@Component
public class ActivityTypeAStrategy implements IActivityStrategy {

    @Override
    public List<ActivityClassListDto> getActivityClassList(long number, int type) {
        System.out.println("ActivityTypeAStrategy: number=" + number +", type="+type);
        return Collections.emptyList();
    }
}
