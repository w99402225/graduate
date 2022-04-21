package edu.zust.se.graduate.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.enums.EventStageEnum;
import edu.zust.se.graduate.mapper.EventMapper;
import edu.zust.se.graduate.response.CodeConstant;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.EventService;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    @Resource
    private EventMapper eventMapper;


    @Override
    public Result addEvent(Event event) {
        if (StringUtil.isNullOrEmpty(event.getName())){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件名不能为空");
        }
        if (StringUtil.isNullOrEmpty(event.getDetails())){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件详情不能为空");
        }
        if (null==event.getTotalMoney()||event.getTotalMoney()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "筹款金额不能为空");
        }
        if (null==event.getRaiseDay()||event.getRaiseDay()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "筹款天数不能为空");
        }
        if (null==event.getType()||event.getType()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件类型不能为空");
        }
        event.setStartDate(LocalDateTime.now());
        event.setStage(EventStageEnum.START.getStatus());
        Boolean success = save(event);
        if (success){
            return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "提交成功");
        }else {
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.SYSTEM_ERROR, "提交失败");
        }
    }

    @Override
    public Result selectEvent(Long id) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<Event> eventList = eventMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",eventList.get(0));
    }

    @Override
    public Result selectEventByType(Integer type) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.orderByAsc("priority");
        List<Event> eventList = eventMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",eventList);
    }
}
