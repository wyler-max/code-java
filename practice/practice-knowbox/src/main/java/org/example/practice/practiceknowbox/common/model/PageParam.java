package org.example.practice.practiceknowbox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yijiu.chen
 * @date 2020/04/16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PageParam extends BusinessParam {
    private int pageNum = 1;

    private int pageSize = 20;

}
