package com.example.udemyTodoListe.email;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(EmailSender.class);


    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage,"utf-8");
            helper.setSubject("Confirm your email");
            helper.setTo(to);
            helper.setText(email, true);
            helper.setFrom("amir@adesso.de");
            mailSender.send(mimeMessage);

        } catch (MessagingException e){
            LOGGER.error("failed to send email", e);
            throw  new IllegalStateException("failed to send email");
        }

    }


}
