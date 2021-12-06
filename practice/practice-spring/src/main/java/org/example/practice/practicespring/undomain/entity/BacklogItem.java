package org.example.practice.practicespring.undomain.entity;

import org.example.practice.practicespring.undomain.enums.BacklogItemStatusType;

/**
 * @author wangyulin
 * @date 2021/9/18
 */
public class BacklogItem extends Entity {

    private int sprintId;
    private BacklogItemStatusType status;

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    public void setBacklogItemStatusType(BacklogItemStatusType status) {
        this.status = status;
    }
}
