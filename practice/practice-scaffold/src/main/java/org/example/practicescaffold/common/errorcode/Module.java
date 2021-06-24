package org.example.practicescaffold.common.errorcode;

import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @desc 模块
 */
@AllArgsConstructor
public enum Module {
    /**
     * 公共、默认模块
     */
    COMMON(0),
    /**
     * 应用01模块
     */
    MODULE_01(1), MODULE_02(2), MODULE_03(3),
    // ......
    /**
     * 位置模块
     */
    UNKNOWN(1000);

    @Getter
    @Setter
    private int type;

    public static Module fromType(final int type) {
        return Stream.of(values()).filter(m -> m.type == type).findAny().orElse(UNKNOWN);
    }
}
