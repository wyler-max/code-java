package org.example.practice.practiceknowbox.common.util;

import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.excpetion.ServiceException;

/**
 * @author yijiu.chen
 * @date 2020/04/22
 */
public class AssertUtil {

    /**
     *
     * @param expr
     * @param msg
     */
    public static void isTrue(boolean expr, String msg) {
        if (!expr) {
            throw new ServiceException(ErrorCode.PARAM_INVALID.getCode(), msg);
        }
    }

    public static void isTrue(boolean expr, ErrorCode errorCode) {
        if (!expr) {
            throw new ServiceException(errorCode);
        }
    }

    public static void isTrue(boolean expr, ErrorCode errorCode, Object... args) {
        if (!expr) {
            throw new ServiceException(errorCode, args);
        }
    }
}
