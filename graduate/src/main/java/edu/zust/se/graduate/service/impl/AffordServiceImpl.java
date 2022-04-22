package edu.zust.se.graduate.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.entity.Afford;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.enums.AffordStateEnum;
import edu.zust.se.graduate.enums.EventStageEnum;
import edu.zust.se.graduate.enums.EventTypeEnum;
import edu.zust.se.graduate.mapper.AffordMapper;
import edu.zust.se.graduate.mapper.EventMapper;
import edu.zust.se.graduate.response.CodeConstant;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.AffordService;
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
public class AffordServiceImpl extends ServiceImpl<AffordMapper, Afford> implements AffordService {
    private static Logger logger = LoggerFactory.getLogger(AffordServiceImpl.class);
    @Resource
    private AffordMapper affordMapper;


    @Override
    public Result addAfford(Afford afford) {
        if (null==afford.getUserId()||afford.getUserId()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "捐款人不能为空");
        }
        if (null==afford.getEventType()||afford.getEventType()==0){
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.ILLEGAL_REQUEST_ERROR, "捐款事件类型不能为空");
        }
        afford.setState(AffordStateEnum.PENDING.getStatus());
        afford.setStartTime(LocalDateTime.now());
        Boolean success = save(afford);
        if (success){
            return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "捐款成功");
        }else {
            return new Result(HttpStatus.BAD_REQUEST, CodeConstant.SYSTEM_ERROR, "捐款失败");
        }
    }

    @Override
    public Result selectAfford(Long id) {
        QueryWrapper<Afford> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        List<Afford> affordList = affordMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",affordList.get(0));
    }

    @Override
    public Result selectAffordByType(Integer eventType) {
        QueryWrapper<Afford> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("event_type", eventType).or().eq("event_type", EventTypeEnum.ALL.getStatus());
        queryWrapper.orderByDesc("money");
        List<Afford> affordList = affordMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",affordList);
    }

    @Override
    public Result selectAffordByUserId(Long userId) {
        QueryWrapper<Afford> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Afford> affordList = affordMapper.selectList(queryWrapper);
        return new Result(HttpStatus.OK, CodeConstant.SUCCESS, "查询成功",affordList);
    }
}
