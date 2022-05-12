package edu.zust.se.graduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.zust.se.graduate.entity.Files;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface FileService extends IService<Files> {

    public Map<String, Object> upload(MultipartFile file) throws IOException;

    public void download(String fileUrl, HttpServletResponse response);
}
