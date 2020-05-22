package com.tanda.tdshop.manager.modules.file;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.entities.tdshop.CourseImgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * create by
 * 三和智控: cxm on 2020/5/21
 */
@RestController
@RequestMapping("/file")
public class ImgVideoFileController {

    @Autowired
    private FileService fileService;


    @RequestMapping("/uploadfile")
    public CourseImgEntity uploadImg(MultipartFile file, HttpServletRequest request){
        return fileService.uploadFile(file,request);
    }

    @RequestMapping("/removefile")
    public void removefile(HttpServletRequest request){
        fileService.removeFile(request);
    }


    @PostMapping("/getfilelist")
    public HashMap getFileList(@RequestBody JSONObject json, HttpServletRequest request){
        return fileService.getFileList(json,request);
    }
}
