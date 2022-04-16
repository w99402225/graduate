package edu.zust.se.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zust.se.graduate.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 潘谦睿
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User findById(Long id);

    List<User> findAll();

    List<User> findByCondition(String account, String nickname, String realName,
                               String telephone, String email, Integer status,
                               Integer userType, Integer outset, Integer pageSize);
}
