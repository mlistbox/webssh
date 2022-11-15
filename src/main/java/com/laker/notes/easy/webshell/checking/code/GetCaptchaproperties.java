package com.laker.notes.easy.webshell.checking.code;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "checkingcode")
public class GetCaptchaproperties {
    private String disturblinenumber;
    private String disturbcolorstar;
    private String disturbcolorend;
    private String codecolorstar;
    private String codecolorend;
    private String codespin;
    private String vcodetype;

    public String getDisturblinenumber() {
        return disturblinenumber;
    }

    public void setDisturblinenumber(String disturblinenumber) {
        this.disturblinenumber = disturblinenumber;
    }

    public String getDisturbcolorstar() {
        return disturbcolorstar;
    }

    public void setDisturbcolorstar(String disturbcolorstar) {
        this.disturbcolorstar = disturbcolorstar;
    }

    public String getDisturbcolorend() {
        return disturbcolorend;
    }

    public void setDisturbcolorend(String disturbcolorend) {
        this.disturbcolorend = disturbcolorend;
    }

    public String getCodecolorstar() {
        return codecolorstar;
    }

    public void setCodecolorstar(String codecolorstar) {
        this.codecolorstar = codecolorstar;
    }

    public String getCodecolorend() {
        return codecolorend;
    }

    public void setCodecolorend(String codecolorend) {
        this.codecolorend = codecolorend;
    }

    public String getCodespin() {
        return codespin;
    }

    public void setCodespin(String codespin) {
        this.codespin = codespin;
    }

    public String getVcodetype() {
        return vcodetype;
    }

    public void setVcodetype(String vcodetype) {
        this.vcodetype = vcodetype;
    }

}
