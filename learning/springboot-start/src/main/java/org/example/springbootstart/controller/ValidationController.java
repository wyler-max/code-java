package org.example.springbootstart.controller;


import org.example.springbootstart.dtos.param.common.Update;
import org.example.springbootstart.dtos.param.user.UserReq;
import org.example.springbootstart.dtos.param.user.UserValidReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation")
public class ValidationController {

    @RequestMapping(value = "/valid1", method = RequestMethod.POST)
    public String valid1(@RequestBody UserReq userReq) {
        Long userId = userReq.getId();
        String name = userReq.getName();
        Integer age = userReq.getAge();
        String email = userReq.getEmail();
        String phone = userReq.getPhone();

        if (name == null || "".equals(name)) {
            log.error("参数：{} 校验失败，原因：{}", "name", "姓名不能为空");
            return "fail";
        }
        if (age == null || age < 18) {
            log.error("参数：{} 校验失败，原因：{}", "age", "年龄必须大于18");
            return "fail";
        }
        if (email == null || "".equals(email) || !email.contains("@")) {
            log.error("参数：{} 校验失败，原因：{}", "email", "邮箱格式错误");
            return "fail";
        }
        if (phone == null || "".equals(phone) || phone.length() != 11) {
            log.error("参数：{} 校验失败，原因：{}", "phone", "非法手机号格式");
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value = "/valid2", method = RequestMethod.POST)
    public String valid2(@Validated @RequestBody UserValidReq userValidReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.error("参数：{} 校验失败，原因：{}", error.getField(), error.getDefaultMessage());
            }
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value = "/valid3", method = RequestMethod.POST)
    public String valid3(@Validated(Update.class) @RequestBody UserValidReq userValidReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.error("参数：{} 校验失败，原因：{}", error.getField(), error.getDefaultMessage());
            }
            return "fail";
        }
        return "success";
    }
}
