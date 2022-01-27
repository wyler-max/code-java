package org.example.practice.practiceknowbox.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author zhangshuai
 * @date 2020/7/23 4:38 下午
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CorpParam extends BusinessParam {

    /**
     * 钉钉组织Id，用于白名单
     */
    private String corpId;
}
