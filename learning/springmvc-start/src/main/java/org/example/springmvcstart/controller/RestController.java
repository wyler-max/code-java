package org.example.springmvcstart.controller;

import org.example.springmvcstart.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 3、Restful 风格
 */
@Controller
@RequestMapping("/rest")
public class RestController {
    /**
     * post 请求：保存
     */
    @RequestMapping(value = "/testRestPOST", method = RequestMethod.POST)
    public String testRestfulURLPOST(User user) {
        System.out.println("rest post" + user);
        return "success";
    }

    /**
     * put 请求：更新
     */
    @RequestMapping(value = "/testRestPUT/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public String testRestfulURLPUT(@PathVariable("id") Integer id, User user) {
        System.out.println("rest put " + id + "," + user);
        return "success";
    }

    /**
     * delete 请求：删除
     */
    @RequestMapping(value = "/testRestDELETE/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String testRestfulURLDELETE(@PathVariable("id") Integer id) {
        System.out.println("rest delete " + id);
        return "success";
    }

    /**
     * post 请求：查询
     */
    @RequestMapping(value = "/testRestGET/{id}", method = RequestMethod.GET)
    public String testRestfulURLGET(@PathVariable("id") Integer id) {
        System.out.println("rest get " + id);
        return "success";
    }
}
