package com.laker.notes.easy.webshell.controller;

import com.laker.notes.easy.webshell.pojo.FileDTO;
import com.laker.notes.easy.webshell.pojo.Result;
import com.laker.notes.easy.webshell.pojo.SFTPData;
import com.laker.notes.easy.webshell.service.SftpService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName SFTPController
 * @Author luo jin jiang
 * @Date 2020/3/21 20:20
 * @Version 1.0
 */
@RestController
@RequestMapping("/sftp")
@Slf4j
public class SFTPController {

    @Autowired
    SftpService sftpService;

    @PostMapping("/connect")
    public Result connect(HttpSession session, @RequestBody SFTPData sftpData) throws Exception {
        try {
            sftpService.connect(session, sftpData);
        }
        catch (NullPointerException n)
        {
            return Result.fail(n.getCause().toString());
        }
        return Result.ok();
    }

    @PostMapping("/exit")
    public Result disConnect(HttpSession session) throws Exception {
        if(session.getAttribute("login").equals("error")) return Result.fail();
        sftpService.disConnect(session);
        session.invalidate();
        return Result.ok();
    }


    @PostMapping("/ls")
    public Result<List<FileDTO>> listFiles(HttpSession session, String path) throws Exception {
        if(session.getAttribute("login").equals("error")) return Result.fail();
        return Result.ok(sftpService.listFiles(session, path));
    }

    @PostMapping("/put")
    public Result put(HttpSession session, MultipartFile uploadFile, String targetDir) throws Exception {
        if(session.getAttribute("login").equals("error")) return Result.fail();
        sftpService.put(session, uploadFile, targetDir);
        return Result.ok();
    }

    @GetMapping("/get")
    public void get(String fullFileName, HttpSession session, HttpServletResponse response) throws Exception {
        if(session.getAttribute("login").equals("error")) return;
        String fileName = fullFileName.substring(fullFileName.lastIndexOf("/") + 1);
        //设置下载响应头
        response.setHeader("content-disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        InputStream inputStream = sftpService.get(session,fullFileName);
        ServletOutputStream outputStream = response.getOutputStream();
        IOUtils.copyLarge(inputStream,outputStream);
    }

    @PostMapping("/delete")
    public Result delete(String fullFileName, HttpSession session) throws Exception {
        if(session.getAttribute("login").equals("error")) return Result.fail();
        sftpService.delete(session,fullFileName);
        return Result.ok();
    }


}
