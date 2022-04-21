package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.response.Result;

public interface EventService extends IService<Event> {

    //新增事件
    Result addEvent(Event event);

    //根据id查找事件
    Result selectEvent(Long id);

    //根据事件类型查找事件，按优先度排序
    Result selectEventByType(Integer type);

}
