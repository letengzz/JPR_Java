package com.hjc.demo.controller;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传
 *
 * @author hjc
 */
@Slf4j
@Controller
public class FileController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @PostMapping("/upload")
    public String upload(HttpSession session,
                         @RequestPart("file") MultipartFile file,
                         @RequestPart("photos") MultipartFile[] photos) throws IOException {
        log.info("上传的信息:file={},photos={}",file.getSize(),photos.length);
        //上传单个文件
        if (!file.isEmpty()){
            //保存到文件服务器，OSS服务器
            String originalFilename = file.getOriginalFilename();
            log.info("上传的文件名:{}",originalFilename);
            //获取上传的文件的后缀名
            assert originalFilename != null;
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            //使用UUID 防止重名，将UUID作为文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");
            //将UUID 和后缀拼接后的结果作为最终的文件名
            String fileName = uuid + suffixName;
            ServletContext servletContext = session.getServletContext();
            String realPath = servletContext.getRealPath("/");
            File filePath = new File(realPath, "img\\");
            if (!filePath.exists()){
                filePath.mkdir();
            }
            file.transferTo(new File(filePath,fileName));
        }
        //上传多个文件
        if (photos.length > 0){
            for (MultipartFile photo :
                    photos) {
                String originalFilename = photo.getOriginalFilename();
                //获取上传的文件的后缀名
                assert originalFilename != null;
                String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
                //使用UUID防止重名 将UUID作为文件名
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                //将uuid和后缀名拼接后的结果作为最终的文件名
                String fileName = uuid + suffixName;

                ServletContext servletContext = session.getServletContext();
                String path = servletContext.getRealPath("/");
                File filePath = new File(path,"img\\");
                if (!filePath.exists()){
                    filePath.mkdirs();
                }
                System.out.println(filePath+fileName);
                photo.transferTo(new File(filePath,fileName));
            }
        }
        return "index";
    }
}
