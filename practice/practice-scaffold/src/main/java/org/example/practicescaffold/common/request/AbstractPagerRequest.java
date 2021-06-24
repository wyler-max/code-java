package org.example.practicescaffold.common.request;

import org.springframework.context.annotation.Description;

import lombok.Data;

/**
 * 使用抽象类是为了禁止实例化
 */
@Data
@Description(value = "分页请求的公共参数")
public abstract class AbstractPagerRequest extends AbstractRequest {

    /**
     * 分页器
     */
    private Pager pager;

    /**
     * 构建分页信息 todo: 未完，需要对接 mybatis或其他
     */
    public void createPageBounds() {
        return;
    }
}
