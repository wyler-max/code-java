package org.example.practicescaffold.controller;

import org.example.practicescaffold.common.exception.ServiceException;
import org.example.practicescaffold.common.model.Response;
import org.example.practicescaffold.common.utils.ResponseUtil;
import org.example.practicescaffold.dtos.param.common.Update;
import org.example.practicescaffold.dtos.param.user.UserValidReq;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(tags = "Swagger2Controller", protocols = "http")
@RestController
@RequestMapping("/swagger2")
public class Swagger2Controller {

    @ApiOperation(value = "/createOrUpdateUser", response = UserValidReq.class, notes = "创建&更新用户")
    @ApiImplicitParams(value = {
        @ApiImplicitParam(name = "ID", value = "id", paramType = "create", dataType = "Number", required = false),
        @ApiImplicitParam(name = "用户名", value = "name", paramType = "create/update", dataType = "String",
            required = true),
        @ApiImplicitParam(name = "年龄", value = "age", paramType = "create/update", dataType = "Number",
            required = true),
        @ApiImplicitParam(name = "邮箱", value = "email", paramType = "create/update", dataType = "String",
            required = true),
        @ApiImplicitParam(name = "手机号", value = "phone", paramType = "create/update", dataType = "String",
            required = true)})
    @RequestMapping(value = "/createOrUpdateUser", method = RequestMethod.POST)
    public UserValidReq createOrUpdateUser(@RequestBody UserValidReq userValidReq) {
        return userValidReq;
    }

    @ApiOperation(value = "/updateUser", response = UserValidReq.class, notes = "更新用户")
    @PostMapping(value = "/updateUser")
    public Response<UserValidReq> updateUser(@Validated(Update.class) @RequestBody UserValidReq userValidReq)
        throws ServiceException {
        /*if (bindingResult.hasErrors()) {
            String errorMsg = "";
            for (FieldError error: bindingResult.getFieldErrors()) {
                errorMsg = String.format("参数：%s 校验失败，原因：%s", error.getField(), error.getDefaultMessage());
                log.error(errorMsg);
                break;
            }
            return RestResponse.fail(errorMsg);
        }*/
        // throw new ServiceException("不想活了");
        UserValidReq user = new UserValidReq();
        BeanUtils.copyProperties(userValidReq, user);
        return ResponseUtil.makeSuccess(user);
    }

    @ApiOperation(value = "/queryUser", response = UserValidReq.class, notes = "查询用户")
    @GetMapping(value = "/queryUser")
    public Response<UserValidReq> queryUser(
        @ApiParam(value = "用户姓名", required = true) @RequestParam(value = "name", defaultValue = "") String name,
        @ApiParam(value = "用户年龄") @RequestParam(value = "age", required = false) Integer age) {

        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.makeFail("用户名为空");
        }
        if (StringUtils.isEmpty(age)) {
            return ResponseUtil.makeFail("用户年龄为空");
        }
        UserValidReq userValidReq = new UserValidReq();
        userValidReq.setName(name);
        userValidReq.setAge(age);
        userValidReq.setId(999L);
        userValidReq.setEmail("query@qq.com");
        userValidReq.setPhone("queryPhoneNumber");
        return ResponseUtil.makeSuccess(userValidReq);
    }
}
