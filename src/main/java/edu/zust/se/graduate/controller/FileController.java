package edu.zust.se.graduate.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import edu.zust.se.graduate.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    FileService fileService;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam MultipartFile file){
        try {
            return fileService.upload(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/{fileUrl}")
    public void download(@PathVariable String fileUrl, HttpServletResponse response) throws IOException {
        fileService.download(fileUrl, response);
    }
}
