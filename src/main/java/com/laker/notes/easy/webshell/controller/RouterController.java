package com.laker.notes.easy.webshell.controller;

import com.laker.notes.easy.webshell.service.impl.WebSSHServiceImpl;
import com.laker.notes.easy.webshell.util.RSAEncrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
//@Controller
@RestController
public class RouterController {
    @Autowired
    WebSSHServiceImpl webSSHService;
    @RequestMapping("/websshpage")
    public ModelAndView websshpage()
    {
        try {
            ModelAndView modelAndView = new ModelAndView();
            //modelAndView.addObject("Host", request.getSession().getAttribute("Host"));
            modelAndView.setViewName("webssh.html");
            return modelAndView;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            ModelAndView modelAndView = new ModelAndView();
            //modelAndView.addObject("Host", request.getSession().getAttribute("Host"));
            modelAndView.setViewName("500.html");
            return modelAndView;
        }
    }

    @RequestMapping("/login")
    public ModelAndView client(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("time1", (new Date()).toString());
        modelAndView.setViewName("login.html");
        return modelAndView;
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
        webSSHService.close((String)request.getSession().getAttribute("uuid"));
        request.getSession().invalidate();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("time1", (new Date()).toString());
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @RequestMapping("/sftp")
    public ModelAndView sftp(){
        try {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("sftp.html");
            return modelAndView;
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
            ModelAndView modelAndView = new ModelAndView();
            //modelAndView.addObject("Host", request.getSession().getAttribute("Host"));
            modelAndView.setViewName("500.html");
            return modelAndView;
        }
    }
    @RequestMapping(value = "/publickey", method = {RequestMethod.POST}, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public String publickey() {
        log.info("公钥:{}",RSAEncrypt.getPublicKeyString());
        return RSAEncrypt.getPublicKeyString();
    }
}
