package edu.zust.se.graduate.controller;

import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.enums.UserTypeEnum;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
@RequestMapping("/api/user")
@Api(tags = {"用户相关接口"}, description = "用户相关接口")
@Slf4j
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return userService.login(user.getAccount(),user.getPassword());
    }

    @RequestMapping("/findById")
    public Result findById(@RequestParam Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/managerSave")
    public Result managerSave(@RequestBody User user){
//        @RequestParam String account,
//        @RequestParam String nickname,
//        @RequestParam Integer userType,
//        @RequestParam(required = false) String email,
//        @RequestParam(required = false) String telephone
        return userService.managerSave(user);
    }

    @GetMapping("/findByCondition")
    public Result findByCondition(@RequestParam(required = false) String account,
                                  @RequestParam(required = false) String nickname,
                                  @RequestParam(required = false) String realName,
                                  @RequestParam(required = false) String telephone,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) Integer status,
                                  @RequestParam(required = false) Integer userType,
                                  @RequestParam(required = false) Integer pageNum,
                                  @RequestParam(required = false) Integer pageSize){
        return userService.findByCondition(account, nickname, realName, telephone, email, status, userType, pageNum, pageSize);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        return userService.adduser(user);
    }

    @PostMapping("/updateUserDetail")
    public Result updateUserDetail(@RequestBody User user){
        return userService.updateUserDetail(user);
    }

    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam Long id,
                                 @RequestParam String password,
                                 @RequestParam String newPassword){
        return userService.updatePassword(id, password, newPassword);
    }
}
