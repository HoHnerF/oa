package com.imooc.oa.controller;

import com.imooc.oa.biz.GlobalBiz;
import com.imooc.oa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.management.relation.RelationTypeSupport;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Controller("globalController")
public class GloablController {
    @Autowired
    private GlobalBiz globalBiz;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String sn, @RequestParam String password){
        Employee employee = globalBiz.login(sn,password);
        if (employee == null) {
            return "redirect:to_login";
        }

        String selected = request.getParameter("remember");

        if("on".equals(selected)){ //"1"表示用户勾选记住密码
            Cookie cookie1 = new Cookie("sn",sn);
            Cookie cookie2 = new Cookie("password",password);
            Cookie cookie3 = new Cookie("checked","on");
            cookie1.setMaxAge(60);
            cookie2.setMaxAge(60);
            cookie3.setMaxAge(60);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);

        }else{
            Cookie cookie1 = new Cookie("sn",null);
            Cookie cookie2 = new Cookie("password",null);
            Cookie cookie3 = new Cookie("checked",null);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.addCookie(cookie3);
        }

        session.setAttribute("employee",employee);
        return "redirect:self";
    }
    @RequestMapping("/self")
    public String self(){
        return  "self";
    }

    @RequestMapping("/quit")
    public String quit(HttpSession session){
        session.setAttribute("employee",null);
        return "redirect:to_login";
    }

    @RequestMapping("/to_change_password")
    public String toChangePassword(){
        return "change_password";
    }

    @RequestMapping("/change_password")
    public String changePassword(HttpSession session, @RequestParam String old, @RequestParam String new1 ,@RequestParam String new2){
        Employee employee = (Employee)session.getAttribute("employee");
        if(employee.getPassword().equals(old)){
            if(new1.equals(new2)){
                employee.setPassword(new1);
                globalBiz.changePassword(employee);
                return "redirect:self";
            }
        }
        return "redirect:to_change_password";
    }

}
