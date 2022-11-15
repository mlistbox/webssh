package com.laker.notes.easy.webshell.checking.code;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

@Slf4j
@WebServlet(urlPatterns = "/getcaptcha.sl", description = "getcaptcha")
public class GetCaptcha extends HttpServlet {

    private int disturblinenumber;/////����������
    private int disturbcolorstar;//////���ɫ��ʼ
    private int disturbcolorend;///////���ɫ����
    private int codecolorstar;//////��֤�����ɫ��ʼ
    private int codecolorend;///////��֤�����ɫ����
    private int codespin;///////��֤����ת�Ƕ�
    private int vcodetype;////////��֤������ 1����2ascii��3����

    public int getDisturblinenumber() {
        return disturblinenumber;
    }

    public void setDisturblinenumber(int disturblinenumber) {
        this.disturblinenumber = disturblinenumber;
    }

    public int getDisturbcolorstar() {
        return disturbcolorstar;
    }

    public void setDisturbcolorstar(int disturbcolorstar) {
        this.disturbcolorstar = disturbcolorstar;
    }

    public int getDisturbcolorend() {
        return disturbcolorend;
    }

    public void setDisturbcolorend(int disturbcolorend) {
        this.disturbcolorend = disturbcolorend;
    }

    public int getCodecolorstar() {
        return codecolorstar;
    }

    public void setCodecolorstar(int codecolorstar) {
        this.codecolorstar = codecolorstar;
    }

    public int getCodecolorend() {
        return codecolorend;
    }

    public void setCodecolorend(int codecolorend) {
        this.codecolorend = codecolorend;
    }

    public int getCodespin() {
        return codespin;
    }

    public void setCodespin(int codespin) {
        this.codespin = codespin;
    }

    public int getVcodetype() {
        return vcodetype;
    }

    public void setVcodetype(int vcodetype) {
        this.vcodetype = vcodetype;
    }

    public GetCaptcha() {
        super();
        //Logger log = LoggerFactory.getLogger(GetCaptcha.class.getName());
        log.info("验证码servlet开始工作");
        // TODO Auto-generated constructor stub
    }

    public static char getRandomChar() {////�����������
        return (char) (0x4e00 + (int) (Math.random() * (0x9fa5 - 0x4e00 + 1)));
    }

    public static char getRandomAscii() {////�������Ӣ��
        return (char) ((int) (Math.random() * (126 - 33) + 33));
    }

    public static char getRandomsimpleAscii() {////�������Ӣ��
        int r = (int) (Math.random() * 62);
        //System.out.println(r);
        //System.out.println((char)(r>10 ? (r<37 ? r+54 : r+60) : r+47));
        return (char) (r > 9 ? (r < 36 ? r + 55 : r + 61) : r + 48);
    }

    public void initParameter() {
        /////System.out.print(this.getServletConfig().getInitParameter("disturblinenumber"));
        disturblinenumber = Integer.valueOf(this.getServletConfig().getInitParameter("disturblinenumber"));
        disturbcolorstar = Integer.valueOf(this.getServletConfig().getInitParameter("disturbcolorstar"));
        disturbcolorend = Integer.valueOf(this.getServletConfig().getInitParameter("disturbcolorend"));
        codecolorstar = Integer.valueOf(this.getServletConfig().getInitParameter("codecolorstar"));
        codecolorend = Integer.valueOf(this.getServletConfig().getInitParameter("codecolorend"));
        codespin = Integer.valueOf(this.getServletConfig().getInitParameter("codespin"));
        vcodetype = Integer.valueOf(this.getServletConfig().getInitParameter("vcodetype"));
    }

    /**
     * The doGet method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to get.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ////initParameter();
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        HttpSession session = request.getSession();
        // ���ڴ��д���ͼ��
        int width = 75, height = 25;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // ��ȡͼ��������
        Graphics g = image.getGraphics();
        // ���������
        Random random = new Random();
        // �趨����ɫ
        Color randcolor = getRandColor(200, 250);
        g.setColor(randcolor);
        g.fillRect(0, 0, width, height);
        // �趨����
        switch (vcodetype) {
            case 2:
                g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                break;
            case 3:
                g.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));
                break;
            case 4:
                g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                break;
            default:
                g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        }
        //g.setFont(new Font("Times New Roman", Font.PLAIN, 20));//////����ASII����ѡ��
        //g.setFont(new Font("Microsoft Yahei", Font.PLAIN, 16));/////��������ѡ��
        // ���߿�
        g.setColor(getRandColor(160, 200));
        g.drawRect(0, 0, width - 1, height - 1);
        // �������155�������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
        g.setColor(getRandColor(disturbcolorstar, disturbcolorend));  /////��Ϊ�򵥵ĵ�ɫ����
        //g.setColor(getRandColor(30, 200)); ////��Ϊ���ӵĵ�ɫ����
        //disturbline=this.getInitParameter("disturbline");
        for (int i = 0; i < this.disturblinenumber; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // ȡ�����������֤��(4λ����)
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand;
            switch (vcodetype) {
                case 2:
                    rand = String.valueOf(this.getRandomAscii());
                    break;
                case 3:
                    rand = String.valueOf(this.getRandomChar());
                    break;
                case 4:
                    rand = String.valueOf(this.getRandomsimpleAscii());
                    break;
                default:
                    rand = String.valueOf(random.nextInt(10));
            }
            //String rand = String.valueOf(random.nextInt(10)); ////��������֤��
            //String rand =String.valueOf(this.getRandomChar()); ////�������������֤��
            //String rand =String.valueOf(this.getRandomAscii()); ////�������ASII��֤��
            sRand += rand;
            // ����֤����ʾ��ͼ����
            g.setColor(new Color(codecolorstar + random.nextInt(codecolorend), codecolorstar + random.nextInt(codecolorend), codecolorstar + random.nextInt(codecolorend)));

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.translate(13 * i + 6, 20);
            int r = random.nextInt(codespin);
            if (r % 2 == 0) r = -r;
            g2d.rotate(r * Math.PI / 180);
            // ���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
            switch (vcodetype) {
                case 2:
                    g2d.drawString(rand, 0, 0);    ////�������ASII��֤��
                    break;
                case 3:
                    g2d.drawString(rand, 0, 0);    ////���������֤��
                    break;
                case 4:
                    g2d.drawString(rand, 0, 0);    ////�������ASII��֤��
                    break;
                default:
                    g2d.drawString(rand, 0, 0);    ////�������ASII��֤��
            }
            /////g2d.drawString(rand, 0 , 0);    ////�������ASII��֤��
            //g.drawString(rand, 18 * i + 3, 20);    ////���������֤��
            // g2d.drawString("", 0, 0);


        }

        // ����֤�����SESSION
        session.setAttribute("vcode", sRand);
        session.setAttribute("vcodetime", new Date());
        // ͼ����Ч
        g.dispose();
        // ���ͼ��ҳ��
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    /**
     * The doPost method of the servlet. <br>
     * <p>
     * This method is called when a form has its tag value method equals to post.
     *
     * @param request  the request send by the client to the server
     * @param response the response send by the server to the client
     * @throws ServletException if an error occurred
     * @throws IOException      if an error occurred
     */
    Color getRandColor(int fc, int bc) {// ������Χ��������ɫ
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


    }

}

