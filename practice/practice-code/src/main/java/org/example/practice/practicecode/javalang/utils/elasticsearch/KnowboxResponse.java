package org.example.practice.practicecode.javalang.utils.elasticsearch;

import lombok.Data;

/**
 *
 */
@Data
public class KnowboxResponse {
    private Integer code;
    private Object data;
    private String msg;
    private Pager pager;

    @Data
    public static class Pager {
        private Integer pageNum;
        private Integer pageSize;
        private Integer count;
    }
}
