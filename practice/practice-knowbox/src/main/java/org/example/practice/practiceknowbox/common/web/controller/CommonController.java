package org.example.practice.practiceknowbox.common.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.example.practice.practiceknowbox.common.enums.ErrorCode;
import org.example.practice.practiceknowbox.common.model.Response;
import org.example.practice.practiceknowbox.common.util.IpUtil;
import org.example.practice.practiceknowbox.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yijiu.chen
 * @date 2020/04/26
 */
@RestController
public class CommonController {

    private volatile boolean healthStatus = true;

    @GetMapping({"/", "/index.html"})
    public String home() {
        return "Hello World!!";
    }

    @GetMapping("/health")
    public String health(HttpServletResponse resp) {
        if (!healthStatus) {
            resp.setStatus(HttpStatus.NOT_FOUND.value());
        }
        return StringUtils.EMPTY;
    }

    @PostMapping("/innerapi/common/manager/enableHealth")
    public Response<Boolean> enableHealth(HttpServletRequest req) {
        if (! IpUtil.isSelfCall(req)) {
            return ResponseUtil.makeFail("DENY");
        }
        healthStatus = true;
        return ResponseUtil.makeSuccess(healthStatus);
    }

    @PostMapping("/innerapi/common/manager/disableHealth")
    public Response<Boolean> disableHealth(HttpServletRequest req) {
        if (!IpUtil.isSelfCall(req)) {
            return ResponseUtil.makeFail("DENY");
        }
        healthStatus = false;
        return ResponseUtil.makeSuccess(healthStatus);
    }

    @GetMapping("/404")
    public Response<Void> notFound() {
        return ResponseUtil.makeError(ErrorCode.NOT_FOUND);
    }

    @GetMapping("/500")
    public Response<Void> internalError() {
        return ResponseUtil.makeError(ErrorCode.UNHANDLER_EXCEPTION);
    }
}
