package com.lichong.wj.controller;

import com.lichong.wj.entity.Result;
import com.lichong.wj.entity.User;
import com.lichong.wj.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserServiceImpl userService;

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "api/login")
    public Result login(@RequestBody User requestUser, HttpSession session) {

        // 对 html 标签进行转义，防止 XSS 攻击
        String userName = requestUser.getUsername();
        User user = userService.get(userName, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            session.setAttribute("user", user);
            return new Result(200);
        }
    }

}
