package org.example.springmvcstart.controller;

import org.example.springmvcstart.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 4、常用注解
 */
@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"}, types = {Integer.class}) // 把msg=迪丽热巴存储到session域对象中
public class AnnoController {

    @RequestMapping(value = "/testRequestMapping",
            method = RequestMethod.GET,
            params = {"username=jack"},
            headers = {"content-type=text/html"})
    public String testRequestMapping(String username) {
        System.out.println("测试RequestMapping注解...");
        System.out.println(username);
        return "success";
    }

    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(name="name") String username,
                                   @RequestParam(name="age", required = false) Integer age)
    {
        System.out.println("执行了...");
        System.out.println(username);
        System.out.println(age);
        return "success";
    }

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody(required = false) String body){
        System.out.println("执行了...");
        System.out.println(body);
        return "success";
    }

    @RequestMapping("/testPathVariable/{sid}")
    public String testPathVariable(@PathVariable(name = "sid") String id){
        System.out.println("执行了...");
        System.out.println(id);
        return "success";
    }

    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept") String header,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws IOException {
        System.out.println("执行了...");
        System.out.println(header);
        //1、return "success";
        //2.1、response.sendRedirect(request.getContextPath()+"/anno/testCookieValue");
        //2.2、return "success";
        return "redirect:testCookieValue";
    }

    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("执行了...");
        System.out.println(cookieValue);
        return "success";
    }

    /**
     * 模拟根据用户名查询用户信息
     */
    private User findUserByName(String uname) {
        User user = new User();
        user.setUname(uname);
        user.setAge(19);
        user.setDate(new Date());
        return user;
    }
    /**
     * 该方法会先执行
     * ModelAttribute 修饰的方法带返回值参数
     */
    //@ModelAttribute
    public User showModel(String uname){
        System.out.println("showModel执行了...");
        User user = findUserByName(uname);
        System.out.println("查询到的用户信息：" + user);
        return user;
    }

    @RequestMapping(value="/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("testModelAttribute执行了...");
        System.out.println(user);
        return "success";
    }

    //@ModelAttribute
    public void showModel2(String uname, Map<String, User> map) {
        User user = findUserByName(uname);
        System.out.println("showModel2方法执行了" + user);
        map.put("abc", user);
    }

    @RequestMapping("/testModelAttribute2")
    public String testModelAttribute2(@ModelAttribute("abc") User user){
        System.out.println("testModelAttribute执行了...");
        // ModelAttribute 获取 abc 给 user pojo赋值，然后用传参覆盖
        System.out.println(user);
        return "success";
    }

    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Model model) {
        System.out.println("testSessionAttributes...");
        // 底层会存储到 request 域对象中
        model.addAttribute("msg", "迪丽热巴");
        return "success";
    }

    @RequestMapping("/getSessionAttributes")
    public String getSessionAttributes(ModelMap modelMap) {
        System.out.println("getSessionAttributes...");
        String msg = (String) modelMap.get("msg");
        System.out.println("msg=" + msg);
        return "success";
    }

    @RequestMapping("/delSessionAttributes")
    public String delSessionAttributes(SessionStatus status) {
        System.out.println("delSessionAttributes...");
        status.setComplete();
        return "success";
    }
}
