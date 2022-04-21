package edu.zust.se.graduate.controller;

import edu.zust.se.graduate.entity.Afford;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.response.Result;
import edu.zust.se.graduate.service.AffordService;
import edu.zust.se.graduate.service.EventService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/afford")
@Api(tags = {"捐款相关接口"}, description = "捐款相关接口")
@Slf4j
public class AffordController {
    @Resource
    AffordService affordService;

    @PostMapping("/affordCreate")
    public Result affordCreate(@RequestBody Afford afford){
        return affordService.addAfford(afford);
    }

    @GetMapping("/selectById")
    public Result selectById(@RequestParam Long affordId){
        return affordService.selectAfford(affordId);
    }

    @GetMapping("/selectAffordByType/{type}")
    public Result selectAffordByType(@PathVariable Integer type){
        return affordService.selectAffordByType(type);
    }
}
