package com.laker.notes.easy.webshell.controller;

import com.laker.notes.easy.webshell.util.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Slf4j
@Controller
public class RouterController {
    @RequestMapping("/websshpage")
    public String websshpage(){
        return "webssh";
    }

    @RequestMapping("/login")
    public String client(){
        return "login";
    }

    @RequestMapping("/sftp")
    public String sftp(){
        return "sftp";
    }
    @RequestMapping(value = "/publickey", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String publickey() {
        log.info(RSAEncrypt.getPublicKeyString());
        return RSAEncrypt.getPublicKeyString();
    }
}
