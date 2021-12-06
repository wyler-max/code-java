package org.example.practice.practicespring.undomain.test;

import org.example.practice.practicespring.undomain.entity.BacklogItem;
import org.example.practice.practicespring.undomain.enums.BacklogItemStatusType;
import org.junit.Test;

/**
 * @author wangyulin
 * @date 2021/9/18
 */
public class TestController {
    public static void main(String[] args) {

    }

    /**
     * 一个scrum模型，需要将一个待定项（backlog item）提交到冲刺（sprint）中
     */
    @Test
    public void testScrum() {
        int sprintId = 10;
        BacklogItem backlogItem = new BacklogItem();
        backlogItem.setSprintId(sprintId);
        backlogItem.setBacklogItemStatusType(BacklogItemStatusType.COMMITED);
    }
}
