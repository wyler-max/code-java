package org.example.springmvcstart.controller;

import org.example.springmvcstart.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 8、拦截器控制器
 */
@Controller
@RequestMapping("/interceptor")
public class InterceptorController {

    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        System.out.println("testInterceptor 执行了...");
        return "success";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginSubmit")
    public String loginSubmit(HttpSession session, String username, String password, Model model){

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(password);

        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("STUDENT_SESSION", student);
            return "homepage";
        }
        model.addAttribute("msg", "用户名或密码错误，请重新登录!");
        return "login";
    }

    @RequestMapping("/main")
    public String toMain(){
        return "homepage";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        // 使 session 过期
        session.invalidate();
        return "redirect:login";
    }
}
