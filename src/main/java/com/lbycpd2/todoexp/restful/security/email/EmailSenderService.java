package com.lbycpd2.todoexp.restful.security.email;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(MimeMessage email){
        javaMailSender.send(email);
    }

    public void sendConfirmationEmail(String userMail, String token) throws MessagingException {

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setFrom("noreply@todoexp.com");
        helper.setTo(userMail);
        helper.setSubject("Confirmation email for todoexp");
        helper.setText("Here is your confirmation link: localhost:8080/register/confirm/" + token, true);

        sendEmail(msg);
    }
}
