package org.example.springbootstart.services;

import org.example.springbootstart.dtos.param.activity.ActivityClassListDto;
import org.example.springbootstart.services.strategy.activity.ActivityFactoryForStrategy;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class ActivityServices {

    @Autowired
    ActivityFactoryForStrategy activityFactoryForStrategy;

    public List<ActivityClassListDto> getActivityClassList(long activityNumber, int type) {
        List<ActivityClassListDto> activityClassList = Lists.newArrayList();
        try {
            activityClassList = activityFactoryForStrategy.getStrategy(type).getActivityClassList(activityNumber, type);
        } catch (Exception e) {
            log.info("=== 未知活动策略类型 ===");
            activityClassList = Collections.emptyList();
        }
        return activityClassList;
    }

}
