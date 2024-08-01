package com.hjc.demo;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class SpringbootEmailApplicationTests {
    @Resource
    private JavaMailSender sender;

    @Resource
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String username;

    /**
     * 发送简单内容
     */
    @Test
    void sendSimpleMessage() {
        //SimpleMailMessage是一个比较简易的邮件封装，支持设置一些比较简单内容
        SimpleMailMessage message = new SimpleMailMessage();
        //邮件发送者，这里要与配置文件中的保持一致
        message.setFrom(username);
        //设置邮件发送给谁，可以多个，这里就发给你的邮箱
        message.setTo("2020885569@qq.com");
        //抄送,收到邮件用户可以看到其他收件人
        message.setCc("2020885569@qq.com");
        //密送 收到邮件用户看不到其他收件人
        message.setBcc("2020885569@qq.com");
        //设置邮件标题
        message.setSubject("【电子科技大学教务处】关于近期学校对您的处分决定");
        //设置邮件内容
        message.setText("XXX同学您好，经监控和教务巡查发现，您近期存在旷课、迟到、早退、上课刷抖音行为，" +
                "现已通知相关辅导员，请手写5000字书面检讨，并在2022年4月1日17点前交到辅导员办公室。");
        //设置当前时间为发送时间
        message.setSentDate(new Date());
        //OK，万事俱备只欠发送
        sender.send(message);
        System.out.println("邮件发送成功");
    }

    @Test
    void sendTextMail() throws MessagingException {
        //创建一个MimeMessage
        MimeMessage message = sender.createMimeMessage();
        //使用MimeMessageHelper来修改MimeMessage中的信息
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //设置邮件发送给谁，可以多个，这里就发给你的邮箱
        helper.setTo("2020885569@qq.com");
        helper.setFrom(username);
        helper.setSentDate(new Date());
        //设置邮件标题
        helper.setSubject("【电子科技大学教务处】关于近期学校对您的处分决定");
        //设置邮件内容
        helper.setText("XXX同学您好，经监控和教务巡查发现，您近期存在旷课、迟到、早退、上课刷抖音行为，" +
                "现已通知相关辅导员，请手写5000字书面检讨，并在2022年4月1日17点前交到辅导员办公室。");
        //抄送,收到邮件用户可以看到其他收件人
        helper.setCc("2020885569@qq.com");
        //密送 收到邮件用户看不到其他收件人
        helper.setBcc("2020885569@qq.com");
        //附件
        File tmpOne = new File("D:\\es\\hello.doc");
        helper.addAttachment(tmpOne.getName(), tmpOne);
        //发送修改好的MimeMessage
        sender.send(message);
        System.out.println("邮件发送成功");
    }


    @Test
    void sendHtmlMail() throws MessagingException {
        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(username);
        helper.setSubject("【电子科技大学教务处】关于近期学校对您的处分决定");
        helper.setTo("2020885569@qq.com");
        //抄送,收到邮件用户可以看到其他收件人
        helper.setCc("2020885569@qq.com");
        //密送 收到邮件用户看不到其他收件人
        helper.setBcc("2020885569@qq.com");
        helper.setSentDate(new Date());
        //生成邮件模板上的内容
        Context context = new Context();
        HashMap<String, String> content = new HashMap<String, String>();
        content.put("username", "hjc");
        content.put("nickname", "hjc");
        content.put("id", "0000001");
        for (String key : content.keySet()) {
            context.setVariable(key, content.get(key));
        }
        String process = templateEngine.process("mail/sendMail.html", context);
        helper.setText(process, true);
        sender.send(mimeMessage);
        System.out.println("邮件发送成功");
    }
}
