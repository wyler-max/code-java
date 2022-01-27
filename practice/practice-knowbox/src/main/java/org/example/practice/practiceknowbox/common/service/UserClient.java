package org.example.practice.practiceknowbox.common.service;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.example.practice.practiceknowbox.common.model.BusinessParam;
import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.model.User;
import org.example.practice.practiceknowbox.common.util.DateUtil;

/**
 * 用户接口，主要用于校验token，后续可以落到各个项目
 *
 * @author yijiu.chen
 * @date 2020/04/16
 */
public interface UserClient {

    String LOCAL_BEAN_NAME = "HOMEWORK_LOCAL_USER_CLIENT";

    String TOKEN_NAME = "token";

    long DEFAULT_EXPIRE_TIME = DateUtil.ONE_WEEK * 2L;

    /**
     * 根据token获取用户信息，如果user拆出微服务，则用FeignClient
     *
     * @param token
     * @return
     */
    Response<User> fetchUserInfoByToken(String token);

    /**
     * 根据code换用户的token
     *
     * @param param
     * @return
     */
    Response<String> fetchTokenByCode(CodeParam param);

    @Data
    class CodeParam extends BusinessParam {
        @NotEmpty(message = "Code不能为null")
        private String code;
    }

}
