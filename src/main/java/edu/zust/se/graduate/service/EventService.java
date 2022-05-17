package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.dto.EventDto;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.response.Result;

public interface EventService extends IService<Event> {

    //新增事件
    Result addEvent(EventDto eventDto);

    //根据id查找事件
    Result selectEvent(Long id);

    //根据事件类型查找事件，按优先度排序
    Result selectEventByType(Integer type);

    //逻辑删除事件
    Result delEvent(Long id);

    //条件查询事件
    Result selectEventByCondition(String name, Integer type, Integer stage, Integer pageNum, Integer pageSize);

    //操作员审批事件
    Result operation(Event event);

    //审查员审批事件
    Result review(Event event);

    //驳回事件
    Result refuse(Event event);

    //条件查询事件
    Result searchEvent(String name, Integer type, Integer stage, Integer pageNum, Integer pageSize);
}
