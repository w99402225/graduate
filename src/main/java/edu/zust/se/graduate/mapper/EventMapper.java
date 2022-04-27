package edu.zust.se.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.entity.User;

import java.util.List;

public interface EventMapper extends BaseMapper<Event> {

    List<Event> findByCondition(String name, Integer type, Integer stage, Integer outset, Integer pageSize);
}
