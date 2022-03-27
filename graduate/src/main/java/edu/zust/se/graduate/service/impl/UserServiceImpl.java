package edu.zust.se.graduate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.mapper.UserMapper;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;
}
