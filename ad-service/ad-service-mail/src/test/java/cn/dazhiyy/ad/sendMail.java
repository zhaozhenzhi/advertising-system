package cn.dazhiyy.ad;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author dazhi
 * @projectName cn.dazhiyy.advert
 * @packageName cn.dazhiyy.ad
 * @className sendMail
 * @description TODO
 * @date 2019/3/18 13:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class sendMail {

        @Autowired
        private JavaMailSender mailSender;

        @Test
        public void sendSimpleMail() throws Exception {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1306123139@qq.com");
            message.setTo("zhaozhenzhi123@gmail.com");
            message.setSubject("主题：简单邮件");
            message.setText("测试邮件内容");

            mailSender.send(message);
        }



}
