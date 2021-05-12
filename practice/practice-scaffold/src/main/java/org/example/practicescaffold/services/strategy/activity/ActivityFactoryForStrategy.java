package org.example.practicescaffold.services.strategy.activity;

import org.example.practicescaffold.services.enums.ActivityTypeEnum;
import org.example.practicescaffold.services.strategy.activity.impl.ActivityTypeAStrategy;
import org.example.practicescaffold.services.strategy.activity.impl.ActivityTypeBStrategy;
import org.example.practicescaffold.services.strategy.activity.impl.ActivityTypeCStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略模式的工厂方法
 */
@Component
public class ActivityFactoryForStrategy {

    private static Map<Integer, IActivityStrategy> strategys = new ConcurrentHashMap<>(3);
    static {
        strategys.put(ActivityTypeEnum.TYPEA.getType(), new ActivityTypeAStrategy());
        strategys.put(ActivityTypeEnum.TYPEB.getType(), new ActivityTypeBStrategy());
        strategys.put(ActivityTypeEnum.TYPEC.getType(), new ActivityTypeCStrategy());
    }

    public IActivityStrategy getStrategy(Integer type) throws Exception{
        IActivityStrategy strategy = strategys.get(type);
        if(strategy == null) {
            throw new RuntimeException("no strategy defined");
        }
        return strategy;
    }
}
