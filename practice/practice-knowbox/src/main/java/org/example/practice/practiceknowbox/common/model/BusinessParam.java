package org.example.practice.practiceknowbox.common.model;

import lombok.Data;

/**
 * 业务参数基类
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
public class BusinessParam {

    private String source;

    private String version;

    private String build_time;

    public void validate() {}
}
