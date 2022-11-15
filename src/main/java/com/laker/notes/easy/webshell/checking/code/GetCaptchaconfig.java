package com.laker.notes.easy.webshell.checking.code;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
@EnableConfigurationProperties(GetCaptchaproperties.class)
public class GetCaptchaconfig {
    //private static Logger log = LoggerFactory.getLogger(GetCaptchaconfig.class.getName());
    @Autowired
    private GetCaptchaproperties properties;

    @Bean
    @ConditionalOnMissingBean(GetCaptcha.class)
    public ServletRegistrationBean servletRegistrationBean() {
        try {
            log.info("开始初始化验证码servlet");
            GetCaptcha g = new GetCaptcha();
            g.setCodecolorend(Integer.valueOf(properties.getCodecolorend()));
            g.setCodecolorstar(Integer.valueOf(properties.getCodecolorstar()));
            g.setCodespin(Integer.valueOf(properties.getCodespin()));
            g.setDisturbcolorend(Integer.valueOf(properties.getDisturbcolorend()));
            g.setDisturbcolorstar(Integer.valueOf(properties.getDisturbcolorstar()));
            g.setDisturblinenumber(Integer.valueOf(properties.getDisturblinenumber()));
            g.setVcodetype(Integer.valueOf(properties.getVcodetype()));
            ServletRegistrationBean registrationBean = new ServletRegistrationBean(g, "/getcaptcha.sl");
            return registrationBean;
        } catch (Exception e) {
            log.error(e.toString());
            log.error("生成验证码servlet失败");
            return null;
        }
    }

}

