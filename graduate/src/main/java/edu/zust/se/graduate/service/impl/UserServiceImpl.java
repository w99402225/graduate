package edu.zust.se.graduate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.dto.UserDto;
import edu.zust.se.graduate.mapper.UserMapper;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.response.CodeConstant;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public Result adduser(User user) {
        userMapper.insert(user);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "提交成功");
    }

    @Override
    public void updateuser(User user) {
        userMapper.updateById(user);
    }

    @Override
    public UserDto findById(Long id) {
        return e2d(findUserById(id));
    }

    @Override
    public UserDto loginByAccount(String account, String password) {
        return null;
    }

    @Override
    public UserDto loginByTel(String tel, String password) {
        return null;
    }

    @Override
    public User findUserById(Long id){
        return userMapper.findById(id);
    }

    @Override
    public Result findByCondition(String account, String nickname, String realName,
                                  String telephone, String email, Integer status,
                                  Integer userType, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        int outset = 0;
        int pages = 1;
        List<User> userListTotal = userMapper.findByCondition(account, nickname, realName, telephone, email, status, userType, null, null);
        if (pageNum!=null&&pageSize!=null){
            outset = (pageNum-1)*pageSize;
            pages =userListTotal.size()/pageSize;
            if(userListTotal.size()%pageSize!=0){
                pages++;
            }
        }
        List<User> userList = userMapper.findByCondition(account, nickname, realName, telephone, email, status, userType, outset, pageSize);
        map.put("userList", userList);
        //若未传size、page，将返回所有结果，所以将current当前页为1，size为总条数传回
        if (pageSize!=null){
            map.put("pageSize",pageSize);
        }else {
            map.put("pageSize",userListTotal.size());
        }
        if (pageNum!=null){
            map.put("pageNum",pageNum);
        }else {
            map.put("pageNum",1);
        }
        map.put("total",userListTotal.size());
        map.put("pages",pages);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功！", map);
    }

    private UserDto e2d(User user) {
        if(user==null) {
            return null;
        }
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    private List<UserDto> e2d(List<User> users) {
        List<UserDto> userDtos=new ArrayList<>();
        if(users!=null&& users.size()>0){
            for(User tuser : users){
                userDtos.add(e2d(tuser));
            }
        }
        return userDtos;
    }
}
