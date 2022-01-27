package org.example.practice.practiceknowbox.dto;

import org.example.practice.practiceknowbox.common.model.BusinessParam;

import lombok.Data;

/**
 * 用户
 */
@Data
public class UserInfo extends BusinessParam {
    private long userId;
    private String userName;
}
