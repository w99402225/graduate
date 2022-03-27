package edu.zust.se.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zust.se.graduate.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
