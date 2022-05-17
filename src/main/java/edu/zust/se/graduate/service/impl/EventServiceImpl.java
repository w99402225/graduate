package edu.zust.se.graduate.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.dto.EventDto;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.entity.Images;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.enums.EventStageEnum;
import edu.zust.se.graduate.enums.UserTypeEnum;
import edu.zust.se.graduate.mapper.EventMapper;
import edu.zust.se.graduate.mapper.ImagesMapper;
import edu.zust.se.graduate.mapper.UserMapper;
import edu.zust.se.graduate.response.CodeConstant;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.EventService;
import edu.zust.se.graduate.util.UpdateUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    private static Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);
    @Resource
    private EventMapper eventMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ImagesMapper imagesMapper;


    @Override
    public Result addEvent(EventDto eventDto) {
        if (StringUtil.isNullOrEmpty(eventDto.getName())){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件名不能为空");
        }
        if (StringUtil.isNullOrEmpty(eventDto.getDetails())){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件详情不能为空");
        }
        if (null==eventDto.getTotalMoney()||eventDto.getTotalMoney()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "筹款金额不能为空");
        }
        if (null==eventDto.getRaiseDay()||eventDto.getRaiseDay()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "筹款天数不能为空");
        }
        if (null==eventDto.getType()||eventDto.getType()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "事件类型不能为空");
        }
        eventDto.setStartDate(LocalDateTime.now());
        eventDto.setEndDate(LocalDateTime.now().plusDays(eventDto.getRaiseDay()));
        eventDto.setStage(EventStageEnum.START.getStatus());
        eventDto.setDeleteType(0);
        Boolean success = save(eventDto);
        if (success){
            if (eventDto.getImages()!=null){
                for (int x=0 ; x<eventDto.getImages().length ;x++){
                    Images images = new Images();
                    images.setFilesId(eventDto.getImages()[x]);
                    images.setEventId(eventDto.getId());
                    imagesMapper.insert(images);
                    if (eventDto.getImg()==null){
                        eventDto.setImg("http://localhost:9090/file/5f98146705614bfb84bbd8e1a71ba506.jpg");
                        eventMapper.updateById(eventDto);
                    }
                }
            }
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
        Map<String, Object> map = new HashMap<>();
        Event event = eventList.get(0);
        EventDto eventDto = new EventDto();
        BeanUtils.copyProperties(event,eventDto);
        User user = userMapper.findById(event.getSubmitUserId());
        eventDto.setSubmitUser(user);
        eventDto.setFilesList(imagesMapper.findByEventId(eventDto.getId()));
        map.put("event",eventDto);
        Duration duration = Duration.between(LocalDateTime.now(),event.getEndDate());
        map.put("day",duration.toDays());
        map.put("hour",duration.toHours()%24);
        map.put("min",duration.toMinutes()%60);
        map.put("second",duration.toMillis()/1000%60);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",map);
    }

    @Override
    public Result selectEventByType(Integer type) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        queryWrapper.eq("delete_type",0);
        queryWrapper.orderByAsc("priority");
        List<Event> eventList = eventMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",eventList);
    }

    @Override
    public Result delEvent(Long id) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<Event> eventList = eventMapper.selectList(queryWrapper);
        Event event = eventList.get(0);
        event.setDeleteType(-1);
        eventMapper.updateById(event);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "删除成功");
    }

    @Override
    public Result selectEventByCondition(String name, Integer type, Integer stage, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        int outset = 0;
        int pages = 1;
        List<EventDto> eventListTotal = eventMapper.findByCondition(name, type, stage, null, null);
        if (pageNum!=null&&pageSize!=null){
            outset = (pageNum-1)*pageSize;
            pages =eventListTotal.size()/pageSize;
            if(eventListTotal.size()%pageSize!=0){
                pages++;
            }
        }
        List<EventDto> eventList = eventMapper.findByCondition(name, type, stage, outset, pageSize);
        for (int x=0; x<eventList.size(); x++){
            eventList.get(x).setSubmitUser(userMapper.findById(eventList.get(x).getSubmitUserId()));
            eventList.get(x).setFilesList(imagesMapper.findByEventId(eventList.get(x).getId()));
        }
        map.put("eventList", eventList);
        //若未传size、page，将返回所有结果，所以将current当前页为1，size为总条数传回
        if (pageSize!=null){
            map.put("pageSize",pageSize);
        }else {
            map.put("pageSize",eventListTotal.size());
        }
        if (pageNum!=null){
            map.put("pageNum",pageNum);
        }else {
            map.put("pageNum",1);
        }
        map.put("total",eventListTotal.size());
        map.put("pages",pages);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功！", map);
    }

    @Override
    public Result operation(Event event) {
        User user =userMapper.findById(event.getOperationUserId());
        if (user.getUserType()!= UserTypeEnum.OPERATION.getStatus()){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "只有操作员可进行此项操作");
        }
        event.setUpdateDate(LocalDateTime.now());
        event.setStage(EventStageEnum.PENDING.getStatus());
        eventMapper.updateById(event);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "审批通过！");
    }

    @Override
    public Result review(Event event) {
        User user =userMapper.findById(event.getReviewUserId());
        if (user.getUserType()!= UserTypeEnum.REVIEW.getStatus()){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "只有审查员可进行此项操作");
        }
        event.setUpdateDate(LocalDateTime.now());
        event.setStage(EventStageEnum.PROGRESS.getStatus());
        eventMapper.updateById(event);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "审批通过！");
    }

    @Override
    public Result refuse(Event event) {
        User user = new User();
        if (event.getReviewUserId()!=null){
            user = userMapper.findById(event.getReviewUserId());
        }
        if (event.getOperationUserId()!=null){
            user = userMapper.findById(event.getOperationUserId());
        }
        if (user.getUserType()!= UserTypeEnum.OPERATION.getStatus()&&user.getUserType()!=UserTypeEnum.REVIEW.getStatus()){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "只有操作员/审查员可进行此项操作");
        }
        event.setUpdateDate(LocalDateTime.now());
        event.setStage(EventStageEnum.ABNORMAL.getStatus());
        eventMapper.updateById(event);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "已成功驳回！");
    }

    @Override
    public Result searchEvent(String name, Integer type, Integer stage, Integer pageNum, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        int outset = 0;
        int pages = 1;
        List<EventDto> eventListTotal = eventMapper.searchEvent(name, type, stage, null, null);
        if (pageNum!=null&&pageSize!=null){
            outset = (pageNum-1)*pageSize;
            pages =eventListTotal.size()/pageSize;
            if(eventListTotal.size()%pageSize!=0){
                pages++;
            }
        }
        List<EventDto> eventList = eventMapper.searchEvent(name, type, stage, outset, pageSize);
        for (int x=0; x<eventList.size(); x++){
            eventList.get(x).setSubmitUser(userMapper.findById(eventList.get(x).getSubmitUserId()));
        }
        map.put("eventList", eventList);
        //若未传size、page，将返回所有结果，所以将current当前页为1，size为总条数传回
        if (pageSize!=null){
            map.put("pageSize",pageSize);
        }else {
            map.put("pageSize",eventListTotal.size());
        }
        if (pageNum!=null){
            map.put("pageNum",pageNum);
        }else {
            map.put("pageNum",1);
        }
        map.put("total",eventListTotal.size());
        map.put("pages",pages);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功！", map);
    }
}
