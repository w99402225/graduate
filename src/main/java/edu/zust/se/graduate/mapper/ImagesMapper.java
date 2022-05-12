package edu.zust.se.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zust.se.graduate.entity.Afford;
import edu.zust.se.graduate.entity.Files;
import edu.zust.se.graduate.entity.Images;

import java.util.List;

public interface ImagesMapper extends BaseMapper<Images> {
    List<Files> findByEventId(Long eventId);
}
