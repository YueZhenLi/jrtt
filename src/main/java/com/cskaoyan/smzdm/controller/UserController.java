package com.cskaoyan.smzdm.controller;

import com.cskaoyan.smzdm.common.Constant;
import com.cskaoyan.smzdm.domain.News;
import com.cskaoyan.smzdm.domain.User;
import com.cskaoyan.smzdm.service.NewsService;
import com.cskaoyan.smzdm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:YueZhenLi
 * @Date:Created in 2018/9/11
 */
@Controller
public class UserController {
    
    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @RequestMapping("/login")
    @ResponseBody
    public Map login(String username, String password, HttpSession session,
                     String rember, HttpServletRequest request, HttpServletResponse response){

        HashMap<Object, Object> hashMap = new HashMap<>();

        User user = userService.findUserByUsernameAndPassword(username, password);
        if (user!=null){
            hashMap.put("code", 0);
            session.setAttribute("user",user);
            if ("1".equals(rember)){
                //cookie中设置用户名
                Cookie usernameCookie = new Cookie(Constant.LOGIN_USERNAME, username);
                //设置cookie存活时间为三天
                usernameCookie.setMaxAge(60*60*24*3);
                usernameCookie.setPath("/");
                response.addCookie(usernameCookie);
                //cookie中设置密码
                Cookie passwordCookie = new Cookie(Constant.LOGIN_PASSWORD, password);
                passwordCookie.setMaxAge(60*60*24*3);
                passwordCookie.setPath("/");
                response.addCookie(passwordCookie);
            }
        }else {
            hashMap.put("code", 1);
            hashMap.put("msgpwd", "密码错误");
        }
        return hashMap;
    }

    @RequestMapping("/reg")
    @ResponseBody
    public Map register(String username, String password){
        boolean register = userService.register(username, password);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        return hashMap;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("user");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.LOGIN_USERNAME.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
                if (Constant.LOGIN_PASSWORD.equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    response.addCookie(cookie);
                }
            }
        }
        return "redirect:/";
    }

    @RequestMapping("/user/addNews")
    @ResponseBody
    public Map addNews(News news, HttpSession session){
        User user = (User) session.getAttribute("user");
        news.setUid(user.getId());
        news.setCreatedDate(new Date());
        Integer integer = newsService.addNews(news);

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        return hashMap;
    }



}
