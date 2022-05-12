package edu.zust.se.graduate.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.zust.se.graduate.entity.Files;

public interface FilesMapper extends BaseMapper<Files> {
    Long addFiles(Files files);
}
