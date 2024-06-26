package com.yz.oj.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //将当前类放入组件中，从而能够实现自动注入
public class SendEmail {

    //引入邮件接口
    @Autowired
    private JavaMailSender mailSender;

    public  void sendEmail(String toEmail){
        String from = "3694264474@qq.com";
        //创建邮件
        SimpleMailMessage message = new SimpleMailMessage();
        //设置发件人信息
        message.setFrom(from);
        //发给谁
        message.setTo(toEmail);
        message.setSubject("您本次的验证码是");
        //生成六位随机验证码
        String verCode = VerCodeGenerateUtil.generateVerCode();
        TimeAndVerCode.verCodeMap.put(toEmail,verCode);
        System.out.println(verCode);
        //获得当前时间
        //TimeAndVerCode.currentTime = new Date();
        TimeAndVerCode.currentTimeMap.put(toEmail,new Date());

        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）");

        mailSender.send(message);
    }
}