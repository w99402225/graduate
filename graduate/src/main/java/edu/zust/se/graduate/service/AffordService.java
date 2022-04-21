package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.entity.Afford;
import edu.zust.se.graduate.entity.Event;
import edu.zust.se.graduate.response.Result;

public interface AffordService extends IService<Afford> {

    //新增捐款
    Result addAfford(Afford afford);

    //根据id查找捐款
    Result selectAfford(Long id);

    //根据事件类型查找捐款，按捐款金额排序
    Result selectAffordByType(Integer eventType);

}
