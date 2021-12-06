package org.example.practice.practicespring.domain.test;

import org.example.practice.practicespring.domain.entity.BacklogItem;
import org.example.practice.practicespring.domain.entity.Sprint;
import org.junit.Test;

/**
 * @author wangyulin
 * @date 2021/9/18
 */
public class TestController {

    @Test
    public void testScrum() {
        Sprint sprint = new Sprint();
        BacklogItem backlogItem = new BacklogItem();
        backlogItem.commitTo(sprint);
    }
}
