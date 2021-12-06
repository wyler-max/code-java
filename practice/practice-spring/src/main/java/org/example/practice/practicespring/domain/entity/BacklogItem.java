package org.example.practice.practicespring.domain.entity;

import org.example.practice.practicespring.domain.enums.BacklogItemStatusType;

/**
 * 待办事项
 */
public class BacklogItem {

    private int sprintId;
    private BacklogItemStatusType status;

    public void setSprintId(int sprintId) {
        this.sprintId = sprintId;
    }

    /**
     * 提交
     */
    public void commitTo(Sprint aSprint) {
        if (!this.isScheduledForRelease()) {
            throw new IllegalStateException("Must be scheduled for release to commit to sprint");
        }
        if (this.isCommitedToSprint()) {
            if (aSprint.sprintId == this.sprintId) {
                this.unCommitedFromSprint();
            }
        }
        this.elevateStatusWith(BacklogItemStatusType.COMMITED);
        this.setSprintId(aSprint.sprintId);
    }

    public boolean isScheduledForRelease() {
        return true;
    }

    public boolean isCommitedToSprint() {
        return false;
    }

    public void unCommitedFromSprint() {
        return;
    }

    public void elevateStatusWith(BacklogItemStatusType status) {
        this.status = status;
    }
}
