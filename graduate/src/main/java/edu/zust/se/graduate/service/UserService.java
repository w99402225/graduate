package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.dto.UserDto;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.response.Result;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserService extends IService<User> {

    Result adduser(User user);

    Result managerSave(User user);

    Result updateUserDetail(User user);

    Boolean accountCheck(String account);

    UserDto findById(Long id);

    UserDto loginByAccount(String account,String password);

    UserDto loginByTel(String tel,String password);

    User findUserById(Long id);

    Result findByCondition(String account, String nickname, String realName,
                           String telephone, String email, Integer status,
                           Integer userType, Integer pageNum, Integer pageSize);
}
