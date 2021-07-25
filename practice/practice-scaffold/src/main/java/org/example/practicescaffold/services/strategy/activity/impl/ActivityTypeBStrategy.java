package org.example.practicescaffold.services.strategy.activity.impl;

import java.util.Collections;
import java.util.List;

import org.example.practicescaffold.dtos.param.activity.ActivityClassListDto;
import org.example.practicescaffold.services.strategy.activity.IActivityStrategy;
import org.springframework.stereotype.Component;

/**
 * B类型活动策略
 */
@Component
public class ActivityTypeBStrategy implements IActivityStrategy {
    @Override
    public List<ActivityClassListDto> getActivityClassList(long number, int type) {
        System.out.println("ActivityTypeBStrategy: number=" + number + ", type=" + type);
        return Collections.emptyList();
    }
}
