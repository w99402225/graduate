package edu.zust.se.graduate.controller;

import edu.zust.se.graduate.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
@Api(tags = {"用户相关接口"}, description = "用户相关接口")
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String home(){
        return "login";
    }
}
