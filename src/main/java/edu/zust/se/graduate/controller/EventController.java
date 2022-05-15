package edu.zust.se.graduate.controller;

import edu.zust.se.graduate.dto.EventDto;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.entity.User;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.EventService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.message.EventMessage;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/event")
@Api(tags = {"捐款事件相关接口"}, description = "捐款事件相关接口")
@Slf4j
public class EventController {
    @Resource
    EventService eventService;

    @PostMapping("/eventSubmit")
    public Result eventSubmit(@RequestBody EventDto eventDto){
        return eventService.addEvent(eventDto);
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Long eventId){
        return eventService.selectEvent(eventId);
    }

    @GetMapping("/selectEventByType/{type}")
    public Result selectEventByType(@PathVariable Integer type){
        return eventService.selectEventByType(type);
    }

    @DeleteMapping("/delById/{id}")
    public Result delById(@PathVariable Long id){
        return eventService.delEvent(id);
    }

    @GetMapping("/selectByCondition")
    public Result selectByCondition(@RequestParam(required = false) String name,
                                    @RequestParam(required = false) Integer type,
                                    @RequestParam(required = false) Integer stage,
                                    @RequestParam(required = false) Integer pageNum,
                                    @RequestParam(required = false) Integer pageSize){
        return eventService.selectEventByCondition(name, type, stage, pageNum, pageSize);
    }

    @GetMapping("/searchEvent")
    public Result searchEvent(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer type,
                              @RequestParam(required = false) Integer stage,
                              @RequestParam(required = false) Integer pageNum,
                              @RequestParam(required = false) Integer pageSize){
        return eventService.searchEvent(name, type, stage, pageNum, pageSize);
    }

    @PostMapping("/operation")
    public Result operation(@RequestBody Event event){
        return eventService.operation(event);
    }

    @PostMapping("/refuse")
    public Result refuse(@RequestBody Event event){
        return eventService.refuse(event);
    }
}
