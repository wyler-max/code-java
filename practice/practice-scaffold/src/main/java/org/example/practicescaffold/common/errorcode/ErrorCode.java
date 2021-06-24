package org.example.practicescaffold.common.errorcode;

public interface ErrorCode {

    /**
     * 获取错误码所在的子模块
     *
     * @return 该错误码所在的子模块
     */
    default Module getModule() {
        return Module.UNKNOWN;
    }

    /**
     * 获取全局的错误码，公式为：模块代码* 10000 + 子模块错误码
     *
     * @return 根据子模块+子模块错误码计算出来的全局错误码
     */
    default int getGlobalErrorCode() {
        return getModule().getType() * 10000 + getErrorCode();
    }

    /**
     * 获取错误码描述
     *
     * @return 错误码描述
     */
    default String getErrorMsg() {
        return "";
    }

    /**
     * 获取子模块定义的错误码
     *
     * @return 子模块定义的错误码
     */
    int getErrorCode();
}
