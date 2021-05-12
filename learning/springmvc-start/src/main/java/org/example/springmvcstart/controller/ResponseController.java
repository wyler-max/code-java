package org.example.springmvcstart.controller;

import org.example.springmvcstart.domain.Account;
import org.example.springmvcstart.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 5、响应数据和结果视图
 */
@Controller
@RequestMapping("/response")
public class ResponseController {

    /**
     * controller 方法返回字符串可以指定逻辑视图名，通过视图解析器解析为物理视图地址。
     * 指定逻辑视图名，经过视图解析器解析为 jsp 物理路径：/WEB-INF/pages/login_success.jsp
     */
    @RequestMapping("/testReturnString")
    public String testReturnString() {
        System.out.println("testReturnString 执行了...");
        //return "success";

        // 使用 关键字 处理返回结果
        // 1、使用关键字转发
        //return "forward:/WEB-INF/pages/login_success.jsp";

        // 2、使用关键字重定向
        return "redirect:/index.jsp";
    }

    /**
     *  Servlet 原始 API 可以作为控制器中方法的参数
     */
    @RequestMapping("/testReturnVoid")
    public void testReturnVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testReturnVoid 执行了...");

        // 使用 servletAPI 处理返回结果
        // 1、请求转发，执行一次
        //request.getRequestDispatcher("/WEB-INF/pages/login_success.jsp").forward(request, response);

        // 2、重定向，执行了2次
        //response.sendRedirect(request.getContextPath()+"/index.jsp");

        // 3、指定响应结果
        // 设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("你好");
        return;
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        System.out.println("testModelAndView 执行了...");
        // 1. 创建 ModelAndView 对象
        ModelAndView modelAndView = new ModelAndView();
        // 2. 模拟从数据库中查询出User对象
        User user = new User();
        user.setUname("迪丽热巴");
        user.setAge(18);
        user.setDate(new Date());
        // 3. 把user对象存储到mv对象中，也会把user对象存入到request对象
        modelAndView.addObject("user", user);
        // 4. 跳转到哪个页面
        modelAndView.setViewName("success");

        return modelAndView;
    }

    /**
     * 模拟异步请求响应
     */
    @RequestMapping("/testAjax")
    public @ResponseBody User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了...");
        // 客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        // 做响应，模拟查询数据库
        user.setUname("迪丽热巴");
        user.setAge(18);
        // 做响应
        return user;
    }

    /**
     * ResponseBody响应json数据
     * 1. mvc:resources标签配置不过滤 (DispatcherServlet会拦截到所有的资源)
     * 2. 使用@RequestBody获取请求体数据
     * 3. 使用@RequestBody注解把json的字符串转换成JavaBean的对象
     * 4. 使用@ResponseBody注解把JavaBean对象转换成json字符串，直接响应
     *
     * 5. json字符串和JavaBean对象互相转换的过程中，需要使用jackson的jar包
     */
    @RequestMapping("/testResponseJson")
    public @ResponseBody Account testResponseJson(@RequestBody Account account) {
        System.out.println("testResponseJson 执行了...");
        System.out.println("Ajax 请求：" + account);
        return account;
    }
}
