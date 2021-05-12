package org.example.practicescaffold.dtos.param.common;

import lombok.Data;

@Data
public class PageParam {
    private int pageNum;

    private int pageSize = 20;
}
