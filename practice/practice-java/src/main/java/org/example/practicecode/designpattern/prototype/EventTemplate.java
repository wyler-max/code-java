package org.example.practicecode.designpattern.prototype;

import lombok.Data;

/**
 * 信件模板
 */
@Data
public class EventTemplate {
    private String eventContent;
    private String eventSubject;

    public EventTemplate(String eventContent, String eventSubject) {
        this.eventContent = eventContent;
        this.eventSubject = eventSubject;
    }
}
