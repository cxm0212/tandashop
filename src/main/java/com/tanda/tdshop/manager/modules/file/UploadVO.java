package com.tanda.tdshop.manager.modules.file;

import lombok.Data;

/**
 * create by
 * 三和智控: cxm on 2020/5/19
 */
@Data
public class UploadVO {

    private String fileName;
    private String saveName;
    private String absoluteSavePath;
    private Long fileSize;
    private String relativePath;
}
