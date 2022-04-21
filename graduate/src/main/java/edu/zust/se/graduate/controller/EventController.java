package edu.zust.se.graduate.controller;

import edu.zust.se.graduate.entity.Event;
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
    public Result eventSubmit(@RequestBody Event event){
        return eventService.addEvent(event);
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Long eventId){
        return eventService.selectEvent(eventId);
    }

    @GetMapping("/selectEventByType/{type}")
    public Result selectEventByType(@PathVariable Integer type){
        return eventService.selectEventByType(type);
    }
}
