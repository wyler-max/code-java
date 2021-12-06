package org.example.practice.practicespring.domain.event;

import org.example.practice.practicespring.domain.entity.BacklogItem;

/**
 * 事件发布器
 */
public class DomainEventPublisher {

    private DomainEventPublisher domainEventPublisher;

    public DomainEventPublisher publish(BacklogItem backlogItem) {
        return this;
    }
}
