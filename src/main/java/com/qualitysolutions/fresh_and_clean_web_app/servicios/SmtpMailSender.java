package com.qualitysolutions.fresh_and_clean_web_app.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class SmtpMailSender {

    @Autowired
    private JavaMailSender javaMailSender;
    public void send(String to, String subject, String body) throws MessagingException
    {
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, multipart, "utf-8");
        helper.setFrom("freshandcleanbarber@gmail.com");
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body,true);
        javaMailSender.send(mensaje);
    }

}
