package com.lichong.wj.controller;

import com.lichong.wj.entity.Result;
import com.lichong.wj.entity.User;
import com.lichong.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @ResponseBody
    @PostMapping(value = "api/login")
    public Result login(@RequestBody User requestUser) {

        // 对 html 标签进行转义，防止 XSS 攻击
        String userName = requestUser.getUsername();
        User user = userService.get(userName, requestUser.getPassword());
        if (null == user) {
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

}
