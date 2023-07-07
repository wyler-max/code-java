package org.example.practicescaffold.controller;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import org.example.practicescaffold.dtos.param.common.Update;
import org.example.practicescaffold.dtos.param.user.UserReq;
import org.example.practicescaffold.dtos.param.user.UserValidReq;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

@Slf4j
@RestController
@RequestMapping("/valid")
public class ValidationController {

    /**
     * 手动校验，可统一封装到请求参数的抽象类中
     */
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

    /**
     * 使用@Validated注解，校验请求参数
     */
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

    /**
     * 使用@Validated注解，校验请求参数，增加了Update.class，可通过添加 groups = Update.class自动判断，是更新还是插入
     */
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

    @Validated
    @GetMapping(value = "/valid4")
    public String valid3(@Valid @RequestParam(value = "p1", defaultValue = "0") @Digits(integer = 100, fraction = 0,
            message = "不是数字呀") long p1) {
        return String.valueOf(p1);
    }
}
