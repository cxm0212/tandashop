package com.tanda.tdshop.manager.modules.file;

import com.alibaba.fastjson.JSONObject;
import com.tanda.tdshop.common.config.IPConfig;
import com.tanda.tdshop.entities.tdshop.CourseImgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Service
public class FileService {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @Autowired
    private FileDao fileDao;

    private String fileParentPath = "/upload";

    private static final Long DEFAULT_SIZE = 10 * 1024 * 1024L;

    /**
     * 上传图片或视频文件
     * @param file
     * @param request
     */
    public CourseImgEntity uploadFile(MultipartFile file, HttpServletRequest request) {

        long courseId = Long.parseLong(request.getParameter("courseId"));
        Long curImgId = Long.parseLong(request.getParameter("curImgId"));

        String[] types = request.getParameter("type").split("_");

        UploadVO uploadVO = fileUpload(file);

        int type = Integer.parseInt(types[1]);

        CourseImgEntity courseImgEntity = new CourseImgEntity();
        courseImgEntity.setCourseId(courseId);
        courseImgEntity.setType(type);
        courseImgEntity.setRelativePath(uploadVO.getRelativePath());
        courseImgEntity.setAbsolutePath(uploadVO.getAbsoluteSavePath());
        courseImgEntity.setOldName(uploadVO.getFileName());
        courseImgEntity.setNewName(uploadVO.getSaveName());
        courseImgEntity.setFileSize(uploadVO.getFileSize());
        courseImgEntity.setUpdateTime(new Date());

        if(curImgId>0){
            //文件已存在,进行数据修改
            courseImgEntity.setId(curImgId);
            fileDao.updateById(courseImgEntity);
        }else{
            //新增对应课程文件
            courseImgEntity.setCreateTime(new Date());
            fileDao.insert(courseImgEntity);
        }


        if(type==0){//缩略图

//        if(types[0].equals("img")){
//        }else if(types[0].equals("video")){
//
//        }
        }else{

        }
        return courseImgEntity;
    }

    public void removeFile(HttpServletRequest request) {
        String curImgId1 = request.getParameter("curImgId");
        long curImgId = Long.parseLong(curImgId1);

        Integer res = fileDao.deleteById(curImgId);
    }

    public HashMap getFileList(JSONObject json, HttpServletRequest request) {

//        int s = Integer.parseInt(request.getParameter("courseId"));
        int courseId = json.getInteger("courseId");
        List<CourseImgEntity> list= fileDao.selectByCourseId(courseId);

        String uriPrefix = IPConfig.uriPrefix();
        HashMap map = new HashMap();
        List loopFileList = new ArrayList();
        CourseImgVideoDTO dto = null;

        for (CourseImgEntity imgEntity : list) {
            int type = imgEntity.getType();

            dto = new CourseImgVideoDTO();
            dto.setId(imgEntity.getId());
            dto.setName(imgEntity.getOldName());
            dto.setStatus("done");
            dto.setUid((0-imgEntity.getId())+"");
            dto.setUrl(uriPrefix +"/"+imgEntity.getRelativePath()+"/"+imgEntity.getNewName());

            if(type==1){
                loopFileList.add(dto);
            }else{
                map.put(type,dto);
            }
        }
        map.put(1,loopFileList);
        return map;
    }



    public UploadVO fileUpload(MultipartFile multipartFile){

        if (null == multipartFile) {
            System.out.println("文件不存在");
        }
        String str = System.getProperty("user.dir").replace("\\", "/");// 获取工程目录
//        String filePath = str+"/src/main"+fileParentPath;
        UploadVO uploadVO = new UploadVO();

        try {
            String newName = makeFileName(multipartFile.getOriginalFilename());
            String savePathSuffix = makePath(newName);
            String relativePath = fileParentPath + "/" + savePathSuffix;
            String saveAbsolutePath = str+"/src/main/resources/static"+relativePath; // upload\2\3

            // File既可以代表文件也可以代表目录
            File file = new File(saveAbsolutePath);
            // 如果目录不存在
            if (!file.exists()) {
                // 创建目录
                file.mkdirs();
            }

            InputStream inputStream = multipartFile.getInputStream();
            FileOutputStream out = new FileOutputStream(saveAbsolutePath + "/" + newName);
            byte buffer[] = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            inputStream.close();
            out.close();



            uploadVO.setFileName(multipartFile.getOriginalFilename());
            uploadVO.setFileSize(multipartFile.getSize());
            uploadVO.setSaveName(newName);
            uploadVO.setAbsoluteSavePath(saveAbsolutePath);
            uploadVO.setRelativePath(relativePath);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return uploadVO;
    }


    /**
     * @param filename 文件的原始名称
     * @return uuid+"_"+文件的原始名称
     * @throws UnsupportedEncodingException
     * @Method: makeFileName
     * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
     * @Anthor:孤傲苍狼
     */
    private String makeFileName(String filename) throws UnsupportedEncodingException { // 2.jpg
        // 为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString() + "_" + URLEncoder.encode(filename, "GBK").replaceAll("%", "");
    }

    /**
     * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
     *
     * @param filename 文件名，要根据文件名生成存储目录
     * @param savePath 文件存储路径
     * @return 新的存储目录
     * @Method: makePath
     * @Description:
     * @Anthor:孤傲苍狼
     */
    private String makePath(String filename) {
        // 得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 0xf; // 0--15
        int dir2 = (hashcode & 0xf0) >> 4; // 0-15
        // 构造新的保存目录
//        String dir = savePath + "/" + dir1 + "/" + dir2; // upload\2\3
//        // upload\3\5
//        // File既可以代表文件也可以代表目录
//        File file = new File(dir);
//        // 如果目录不存在
//        if (!file.exists()) {
//            // 创建目录
//            file.mkdirs();
//        }
        return dir1 + "/" + dir2;
    }
}
