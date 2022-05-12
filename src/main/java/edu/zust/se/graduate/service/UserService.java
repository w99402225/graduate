package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.dto.UserDto;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.response.Result;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface UserService extends IService<User> {

//    Result uploadAvatar(MultipartFile file);

    Result adduser(User user);

    Result managerSave(User user);

    Result updateUserDetail(User user);

    Result login(String account, String password);

    Result accountCheck(String account);

    Result updatePassword(Long id, String password, String newPassword);

    Result findById(Long id);

    UserDto loginByAccount(String account,String password);

    UserDto loginByTel(String tel,String password);

    User findUserById(Long id);

    Result findByCondition(String account, String nickname, String realName,
                           String telephone, String email, Integer status,
                           Integer userType, Integer pageNum, Integer pageSize);
}
