package edu.zust.se.graduate.service.impl;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zust.se.graduate.entity.Files;
import edu.zust.se.graduate.mapper.FilesMapper;
import edu.zust.se.graduate.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class FileServiceImpl extends ServiceImpl<FilesMapper, Files> implements FileService {
    private static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    @Resource
    private FilesMapper filesMapper;

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Override
    public Map<String, Object> upload(@RequestParam MultipartFile file) throws IOException{
        String originalFileName = file.getOriginalFilename();
        String type = FileUtil.extName(originalFileName);
        long size = file.getSize();
        File upload = new File(fileUploadPath);
        if (!upload.exists()){
            upload.mkdirs();
        }
        //定义文件唯一标识码
        String uuid = IdUtil.fastSimpleUUID();
        String fileUrl = uuid + StrUtil.DOT + type;
        File uploadFile = new File(fileUploadPath + fileUrl);
        //查询md5是否存在，若存在则直接用已有的url
        String md5 = SecureUtil.md5(file.getInputStream());
        QueryWrapper<Files> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("md5",md5);
        List<Files> filesList = filesMapper.selectList(queryWrapper);
        String url;
        if (filesList.size()!=0){
            url = filesList.get(0).getUrl();
        }else {
            url = "http://localhost:9090/file/"+fileUrl;
            //将文件存到指定位置
            file.transferTo(uploadFile);
        }
        Files files = new Files();
        files.setDeleteType(0);
        files.setSize(size/1024);
        files.setName(originalFileName);
        files.setType(type);
        files.setUrl(url);
        files.setMd5(md5);
        filesMapper.addFiles(files);
        Long id = files.getId();
        Map<String, Object> map = new HashMap<>();
        map.put("url",url);
        map.put("imgId",id);
        map.put("imgName",originalFileName);
        return map;
    }

    @Override
    public void download(String fileUrl, HttpServletResponse response) {
        File file = new File(fileUploadPath +fileUrl);
        //设置输出流格式
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(fileUrl,"UTF-8"));
            response.setContentType("application/octet-stream");
            outputStream.write(FileUtil.readBytes(file));
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
