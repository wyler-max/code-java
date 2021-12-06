package org.example.practice.practicespring.domain.controller;

import org.example.practice.practicespring.db.model.User;
import org.example.practice.practicespring.domain.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyulin
 * @date 2021/9/17
 */
@RestController
@RequestMapping("/domain")
public class UserController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/changePersonalName")
    public void changePersonalName(@RequestParam("name") String name) {
        long customerId = 10;
        User user = customerRepository.selectById(customerId);
        if (user == null) {
            throw new IllegalStateException("用户不存在");
        }

    }
}
